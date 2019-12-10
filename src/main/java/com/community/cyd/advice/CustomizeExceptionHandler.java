package com.community.cyd.advice;

import com.alibaba.fastjson.JSON;
import com.community.cyd.dto.ResultDTO;
import com.community.cyd.exception.CustomizeErrorCode;
import com.community.cyd.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 捕获异常，并返回到前端
 **/
@ControllerAdvice
public class CustomizeExceptionHandler {
    //申明捕获的异常类，这里Exception表示所有异常都捕获 (处理常规异常)
    @ExceptionHandler(Exception.class)
    Object handleControllerException(Throwable e, Model model,
                                     HttpServletRequest request, HttpServletResponse response) {
        String contentType = request.getContentType();
        ResultDTO resultDTO;
        //如果是json对象返回json
        if ("application/json".equals(contentType)) {
            if (e instanceof CustomizeException) {
                resultDTO = ResultDTO.errorOf((CustomizeException) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter printWriter = response.getWriter();
                printWriter.write(JSON.toJSONString(resultDTO));
                printWriter.close();
            } catch (IOException ex) {
            }
            return null;
        } else {     //否则返回error页面
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");  //返回error页面
        }

    }

}
