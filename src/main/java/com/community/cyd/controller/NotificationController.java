package com.community.cyd.controller;

import com.community.cyd.dto.NotificationDTO;
import com.community.cyd.enums.NotificationTypeEnum;
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
     * 点击通知列表中的问题时跳转
     */
    @GetMapping("/profile/{id}")
    public String profile(@PathVariable(name = "id") Long id, HttpServletRequest request) {
        //获取user（如果未登录则被拦截）
        User user = (User) request.getSession().getAttribute("user");
        //如果未登陆，即拦截器中没有添加user session，则不能访问profile，并跳回首页。
        if (user == null) {
            return "redirect:/";
        }

        //点击问题时触发
        NotificationDTO notificationDTO = notificationService.read(id, user);

        //跳转到对应id的question页面
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
            return "redirect:/question/" + notificationDTO.getOuterId();
        } else {
            return "redirect:/";
        }
    }
}
