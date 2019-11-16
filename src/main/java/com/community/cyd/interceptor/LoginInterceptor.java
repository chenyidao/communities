package com.community.cyd.interceptor;

import com.community.cyd.model.User;
import com.community.cyd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//创建一个拦截器实现HandlerInterceptor接口

/**
 * preHandle
 * 调用时间：Controller方法处理之前
 * 执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序一个接一个执行
 * 若返回false，则中断执行，注意：不会进入afterCompletion
 * <p>
 * postHandle
 * 调用前提：preHandle返回true
 * 调用时间：Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作
 * 执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序倒着执行。
 * 备注：postHandle虽然post打头，但post、get方法都能处理
 * <p>
 * afterCompletion
 * 调用前提：preHandle返回true
 * 调用时间：DispatcherServlet进行视图的渲染之后
 * 多用于清理资源
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String value = cookie.getValue();
                    User user = userService.findByToken(value);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("start postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("start after Completion");
    }
}
