package com.dahuatech.springboot.configuration;

import com.dahuatech.springboot.bean.ResponseBean;
import com.dahuatech.springboot.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerConfiguration {

    @ExceptionHandler(GlobalException.class)
    public ResponseBean throwable(GlobalException globalException) {
        log.info("code: {}, msg: {}", globalException.getCode(), globalException.getMsg());
        return new ResponseBean(globalException.getCode(), globalException.getMsg());
    }
}
