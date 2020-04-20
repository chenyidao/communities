package com.community.cyd.controller;

import com.community.cyd.dto.CommentReqDTO;
import com.community.cyd.dto.CommentRespDTO;
import com.community.cyd.dto.ResultDTO;
import com.community.cyd.enums.CommentTypeEnum;
import com.community.cyd.exception.CustomizeErrorCode;
import com.community.cyd.model.Comment;
import com.community.cyd.model.User;
import com.community.cyd.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 评论
 **/
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * @RequestBody 接受json对象, 自动反序列化为java对象
     * @ResponseBody 返回json对象(自动转换为json对象)
     **/
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentReqDTO commentDTO,
                       HttpServletRequest request) {

        //是否登陆
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }

        //判断是否评论为空
        if (commentDTO.getContent() == null || StringUtils.isBlank(commentDTO.getContent())) {
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
        }

        //插入评论
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        comment.setCommentCount(0);
        commentService.insert(comment,user);
        return ResultDTO.okOf(CustomizeErrorCode.SUCCESS);
    }

    /**
     * 获取二级评论
     **/
    @ResponseBody
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public ResultDTO<List<CommentRespDTO>> getSubComment(@PathVariable(name = "id") Long id, Model model) {
        List<CommentRespDTO> commentRespDTOS = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
        model.addAttribute("subComments",commentRespDTOS);
        return ResultDTO.okOf(commentRespDTOS);
    }
}
