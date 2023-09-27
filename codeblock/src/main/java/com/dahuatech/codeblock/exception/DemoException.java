package com.dahuatech.codeblock.exception;

import lombok.Data;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.test.exception</p>
 * <p>className: DemoException</p>
 * <p>date: 2023/7/19</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 * @description 异常类定义模板
 */

@Data
public class DemoException extends RuntimeException {
    /**
     * 1、输出结果说明
     *    System.out.println(e); // com.dahuatech.test.exception.DemoException: exception msg
     *    System.out.println(e.getMessage()); // exception msg
     *    System.out.println(e.getCause()); // 一般是null 因为一般自定义或者系统异常类如ArithmeticException都不会给cause赋值
     *
     * 2、注意: 一般情况下不会直接在标准输出打印出来 都是打到日志里
     *
     * 3、日志打印规范
     *  3.1 一般来说如果条件不满足打 logger.warn("xxxxxxxxxxxxxxxxxxx")提示信息
     *  3.2 如果是抛出异常则打 logger.error("不满足的原因", exceptionObj)
     */
    private static final long serialVersionUID = 6475316940159183080L;

    // 自定义异常类属性
    private int code;

    public DemoException() {
        // 使用父类默认空参构造器 一般不会使用
        super();
    }

    public DemoException(String message) {
        // 使用父类默认的构造器 一般会使用
        super(message);
    }

    public DemoException(String message, int code) {
        // 使用父类默认的构造器 如果需要自定义属性要使用
        super(message);
        // 自定义异常类属性赋值
        this.code = code;
    }
}