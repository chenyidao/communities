package com.community.cyd.service;

import com.community.cyd.enums.CommentTypeEnum;
import com.community.cyd.exception.CustomizeErrorCode;
import com.community.cyd.exception.CustomizeException;
import com.community.cyd.mapper.CommentMapper;
import com.community.cyd.mapper.QuestionExtendMapper;
import com.community.cyd.mapper.QuestionMapper;
import com.community.cyd.model.Comment;
import com.community.cyd.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionExtendMapper questionExtendMapper;

    /**
     * 插入评论
     **/
    @Transactional
    public void insert(Comment comment) {
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
            commentMapper.insert(comment);
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            //插入评论
            commentMapper.insert(comment);
            //评论数+1
            question.setCommentCount(1);
            questionExtendMapper.incCommentCount(question);
        }
    }
}
