package com.dahuatech.test.bean;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.test.bean</p>
 * <p>className: Singleton</p>
 * <p>date: 2023/5/4</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
public class Singleton {
    private String name;
    private int age;

    private static final Singleton instance = new Singleton("alan", 23);

    private Singleton(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Singleton getInstance() {
        return instance;
    }
}
