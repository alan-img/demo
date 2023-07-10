package com.dahuatech.springboot.exception;

public class GlobalException extends BaseException {
    public GlobalException(int code, String msg) {
        super(code, msg);
    }

    public GlobalException() {
    }
}
