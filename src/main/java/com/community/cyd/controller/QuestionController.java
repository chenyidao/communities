package com.community.cyd.controller;

import com.community.cyd.dto.CommentRespDTO;
import com.community.cyd.dto.QuestionDTO;
import com.community.cyd.enums.CommentTypeEnum;
import com.community.cyd.model.Question;
import com.community.cyd.service.CommentService;
import com.community.cyd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 问题详情（对已发布的问题的详情，可编辑等操作）
 **/



@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String questionDetail(@PathVariable(name = "id") Long id,
                                 Model model) {
        QuestionDTO questionDTO = questionService.getById(id);

        List<QuestionDTO> relatedQuestions = questionService.selectRelatedByTag(questionDTO);
        //获取问题的comment评论
        List<CommentRespDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        model.addAttribute("comments", comments);
        model.addAttribute("question", questionDTO);
        model.addAttribute("relatedQuestions", relatedQuestions);
        questionService.incViewCount(id);
        return "question";
    }


}
