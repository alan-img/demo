package com.dahuatech.springboot.configuration;

import com.dahuatech.springboot.interceptor.CommonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HandlerInterceptorConfiguration implements WebMvcConfigurer {

    @Autowired
    private CommonInterceptor loggerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor)
                .addPathPatterns("/**"); // 拦截所有请求
    }
}
