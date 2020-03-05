package com.community.cyd.controller;

import com.community.cyd.dto.PaginationDTO;
import com.community.cyd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
/****
 * 首页
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {   //page 页号   size 每页大小
        PaginationDTO paginationDTO = questionService.questionList(page, size);   //获取问题列表
        model.addAttribute("pagination", paginationDTO);
        return "index";
    }
}