package com.dahuatech.codeblock.singleton;

import java.util.Objects;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.singleton</p>
 * <p>className: Singleton</p>
 * <p>date: 2023/5/4</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 * @description DCL(double-check-locking)双重检查锁定实现线程安全的单例模式
 */

public class SingletonDCL {
    private String name;
    private int age;

    private SingletonDCL(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private static volatile SingletonDCL singleton;

    public static SingletonDCL getInstance() {
        if (Objects.isNull(singleton)) {
            synchronized (SingletonDCL.class) {
                if (Objects.isNull(singleton)) {
                    singleton = new SingletonDCL("alan", 23);
                }
            }
        }

        return singleton;
    }
}
