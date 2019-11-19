package com.community.cyd.controller;

import com.community.cyd.dto.QuestionDTO;
import com.community.cyd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 问题详情（对已发布的问题的详情，可编辑等操作）
 **/
@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/question/{id}")
    public String questionDetail(@PathVariable(name = "id") Integer id,
                                 Model model) {
        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question", questionDTO);
        questionService.incViewCount(id);
        return "question";
    }
}
