package com.dahuatech.okhttp.utils;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.utils</p>
 * <p>className: MediaTypeEnum</p>
 * <p>date: 2023/4/4</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
public enum MediaTypeEnum {
    APPLICATION_JSON_CHARSET_UTF_8("application/json;charset=utf-8");

    private final String value;

    MediaTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}