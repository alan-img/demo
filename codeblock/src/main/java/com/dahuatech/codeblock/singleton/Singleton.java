package com.dahuatech.codeblock.singleton;

import java.io.Serializable;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.singleton</p>
 * <p>className: Singleton</p>
 * <p>date: 2023/5/5</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 * @description 使用类加载机制天然实现线程安全的单例模式
 */

public class Singleton {
    private String name;
    private int age;

    private Singleton(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private static final Singleton instance = new Singleton("alan", 23);

    public static Singleton getInstance() {
        return instance;
    }
}
