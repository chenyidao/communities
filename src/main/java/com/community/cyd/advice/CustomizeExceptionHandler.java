package com.community.cyd.advice;

import com.community.cyd.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomizeExceptionHandler {

    //申明捕获的异常类，这里Exception表示所有异常都捕获 (处理常规异常)
    @ExceptionHandler(Exception.class)
    ModelAndView handleControllerException(HttpServletRequest request, Throwable e, Model model) {
//        HttpStatus status = getStatus(request);
        if (e instanceof CustomizeException) {
            model.addAttribute("message", e.getMessage());
        } else {
            model.addAttribute("message", "服务器冒烟了，要不然您稍后试试？！！");
        }
        return new ModelAndView("error");  //返回error页面
    }

    /*
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
    */
}
