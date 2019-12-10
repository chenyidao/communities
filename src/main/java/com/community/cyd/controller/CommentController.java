package com.community.cyd.controller;

import com.community.cyd.dto.CommentDTO;
import com.community.cyd.dto.ResultDTO;
import com.community.cyd.exception.CustomizeErrorCode;
import com.community.cyd.model.Comment;
import com.community.cyd.model.User;
import com.community.cyd.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论
 **/
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * @RequestBody 接受json对象,自动反序列化为java对象
     * @ResponseBody 返回json对象(自动转换为json对象)
     **/
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request) {

        //是否登陆
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }

        //插入评论
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(1L);
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf(CustomizeErrorCode.SUCCESS);
    }
}
