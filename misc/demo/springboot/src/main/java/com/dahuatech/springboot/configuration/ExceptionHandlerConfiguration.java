package com.dahuatech.springboot.configuration;

import com.dahuatech.springboot.exception.RequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerConfiguration {
    @ExceptionHandler(RequestException.class)
    public String throwable(RequestException requestException) {
        log.info("requestException: {}", requestException.getMessage());
        return requestException.getMessage();
    }
}
