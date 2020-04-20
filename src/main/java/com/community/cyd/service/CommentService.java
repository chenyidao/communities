package com.community.cyd.service;

import com.community.cyd.dto.CommentRespDTO;
import com.community.cyd.enums.CommentTypeEnum;
import com.community.cyd.enums.NotificationStatusEnum;
import com.community.cyd.enums.NotificationTypeEnum;
import com.community.cyd.exception.CustomizeErrorCode;
import com.community.cyd.exception.CustomizeException;
import com.community.cyd.mapper.*;
import com.community.cyd.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtendMapper questionExtendMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommentExtendMapper commentExtendMapper;

    @Autowired
    private NotificationMapper notificationMapper;

    /**
     * 插入评论
     **/
    @Transactional
    public void insert(Comment comment, User commentator) {   //因为正在登陆的人进行评论，所以notifier应该是目前的user
        //如果评论用户不存在
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        //如果评论类型不存在
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        //评论回复或是评论问题
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            //通过一级评论判断，如果问题存在，则获取到问题的title
            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }

            commentMapper.insert(comment);

            //子评论数+1
            dbComment.setCommentCount(1);
            commentExtendMapper.incCommentCount(dbComment);

            //添加通知
            CreatNotify(comment, dbComment.getCommentator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_COMMENT);
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //插入评论
            commentMapper.insert(comment);
            //回复问题的评论数+1
            question.setCommentCount(1);
            questionExtendMapper.incCommentCount(question);

            //创建通知
            CreatNotify(comment, question.getCreator(), commentator.getName(), question.getTitle(), NotificationTypeEnum.REPLY_QUESTION);
        }
    }

    /**
     * 创建回复通知重构函数
     */
    private void CreatNotify(Comment comment, Long receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationTypeEnum) {
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(notificationTypeEnum.getType());  //回复类型
        notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());   //回复状态
        notification.setNotifier(comment.getCommentator());      //评论者
        notification.setOuterId(comment.getParentId());          //被评论的评论id
        notification.setReceive(receiver);     //被评论者
        notification.setNotifierName(notifierName);   //评论人的名字
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    /**
     * 获取评论
     **/
    public List<CommentRespDTO> listByTargetId(Long id, CommentTypeEnum type) {

        //通过评论parentId和评论类型type获取所有评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria()
                .andParentIdEqualTo(id)
                .andTypeEqualTo(type.getType());
        //将评论按照创建时间降序排序
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(commentExample);

        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        //通过评论人commentator获取去重的评论人id集合  commentators
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        List<Long> userIds = new ArrayList<>();
        userIds.addAll(commentators);

        //获取评论人并转换为Map(降低时间复杂度)
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andIdIn(userIds);
        List<User> users = userMapper.selectByExample(userExample);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));

        //转换comment为commentDTO(包括user)
        List<CommentRespDTO> commentRespDTOS = comments.stream().map(comment -> {
            CommentRespDTO commentRespDTO = new CommentRespDTO();
            BeanUtils.copyProperties(comment, commentRespDTO);
            commentRespDTO.setUser(userMap.get(comment.getCommentator()));
            return commentRespDTO;
        }).collect(Collectors.toList());

        return commentRespDTOS;
    }
}
