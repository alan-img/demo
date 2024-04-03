package com.dahuatech.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = 789579173691322730L;

    // 响应码
    protected int code;
    // 响应消息
    protected String msg;
}