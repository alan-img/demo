package com.dahuatech.springboot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseException extends RuntimeException {
    // 响应码
    protected int code;
    // 响应消息
    protected String msg;
}