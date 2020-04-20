package com.community.cyd.controller;

import com.community.cyd.dto.NotificationDTO;
import com.community.cyd.model.User;
import com.community.cyd.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * 与通知相关的Controller
 */
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * 点击通知跳转
     */
    /*@GetMapping("/profile/{id}")
    public String profile(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        //获取user（如果未登录则被拦截）
        User user = (User) request.getSession().getAttribute("user");
        //如果未登陆，即拦截器中没有添加user session，则不能访问profile，并跳回首页。
        if (user == null) {
            return "redirect:/";
        }

//        NotificationDTO notificationDTO = notificationService.read(id, user);
        return "index";
    }*/
}
