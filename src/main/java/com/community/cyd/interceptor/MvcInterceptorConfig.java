package com.community.cyd.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 配置拦截器
 **/
//新建配置类来管理拦截器，并将拦截器注入其中
@Configuration
//@EnableWebMvc  使用时该标签表示自己配置静态资源路径，否则将"/static/**,/public/**等静态资源路径 排除拦截"啊
public class MvcInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    //    private final String[] excludePath = {"/static/**","/callback","/"};
    // 多个拦截器组成一个拦截器链
    // addPathPatterns 用于添加拦截规则，/**表示拦截所有请求
    // excludePathPatterns 用户排除拦截
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
    }
}
