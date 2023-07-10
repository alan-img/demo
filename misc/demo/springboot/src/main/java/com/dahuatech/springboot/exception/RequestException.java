package com.dahuatech.springboot.exception;

import lombok.Data;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.exception</p>
 * <p>className: RequestException</p>
 * <p>date: 2023/3/26</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
@Data
public class RequestException extends RuntimeException {
    private int code;

    public RequestException() {
    }

    public RequestException(String message, int code) {
        super(message);
        this.code = code;
    }
}
