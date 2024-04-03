package com.dahuatech.springboot.exception;

public class CustomException extends BaseException {
    private static final long serialVersionUID = 4949002138956157200L;

    public CustomException(int code, String msg) {
        super(code, msg);
    }

    public CustomException() {
    }
}
