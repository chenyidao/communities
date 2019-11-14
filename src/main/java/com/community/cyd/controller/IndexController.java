package com.community.cyd.controller;

import com.community.cyd.dto.QuestionDTO;
import com.community.cyd.model.User;
import com.community.cyd.service.QuestionService;
import com.community.cyd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String value = cookie.getValue();
                    User user = userService.findByToken(value);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        List<QuestionDTO> questionDTOList = questionService.questionList();   //获取问题列表
        model.addAttribute("questions", questionDTOList);
        return "index";
    }
}
