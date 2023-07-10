package com.dahuatech.codeblock.enumeration;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.enumeration</p>
 * <p>className: MyEnum</p>
 * <p>date: 2023/5/29</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */

public enum MyEnum {
    ENUM_CONSTANT_1("constant1Value"),
    ENUM_CONSTANT_2("constant2Value"),
    ENUM_CONSTANT_3("constant3Value");

    private final String value;

    MyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
