package com.community.cyd.controller;

import com.community.cyd.dto.PaginationDTO;
import com.community.cyd.model.User;
import com.community.cyd.service.NotificationService;
import com.community.cyd.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/****
 * 我的问题
 */
@Controller
public class ProfileController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/profile/questions")
    public String profileQuestion(Model model,
                                  HttpServletRequest request,
                                  @RequestParam(name = "page", defaultValue = "1") Integer page,
                                  @RequestParam(name = "size", defaultValue = "5") Integer size) {
        //获取user（如果未登录则被拦截）
        User user = (User) request.getSession().getAttribute("user");
        //如果未登陆，即拦截器中没有添加user session，则不能访问profile，并跳回首页。
        if (user == null) {
            return "redirect:/";
        }
        model.addAttribute("section", "questions");
        model.addAttribute("sectionName", "我的提问");
        PaginationDTO paginationDTO = questionService.getListByUserId(user.getId(), page, size);   //获取问题列表
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }

    @GetMapping("/profile/replies")
    public String profileReplies(Model model,
                                 HttpServletRequest request,
                                 @RequestParam(name = "page", defaultValue = "1") Integer page,
                                 @RequestParam(name = "size", defaultValue = "5") Integer size) {
        //获取user（如果未登录则被拦截）
        User user = (User) request.getSession().getAttribute("user");
        //如果未登陆，即拦截器中没有添加user session，则不能访问profile，并跳回首页。
        if (user == null) {
            return "redirect:/";
        }
        PaginationDTO paginationDTO = notificationService.getListByUserId(user.getId(), page, size);
        model.addAttribute("section", "replies");
        model.addAttribute("sectionName", "最新回复");
        model.addAttribute("pagination", paginationDTO);
        return "profile";
    }

    /*个人信息*/
    @GetMapping("/profile/user")
    public String getUser(HttpServletRequest request) {
        //获取user（如果未登录则被拦截）
        User user = (User) request.getSession().getAttribute("user");
        //如果未登陆，即拦截器中没有添加user session，则不能访问profile，并跳回首页。
        if (user == null) {
            return "redirect:/";
        }
        return "user";
    }
}
