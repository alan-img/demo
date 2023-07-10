package com.dahuatech.pool.demo;

import com.dahuatech.pool.objectpool.PersonObjectPool;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.pool.demo</p>
 * <p>className: JavaDemo</p>
 * <p>date: 2023/6/5</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */

public class JavaDemo {
    public static void main(String[] args) {
        PersonObjectPool pool = new PersonObjectPool();
        System.out.println(pool.get());
        System.out.println("pool.getNumActive() = " + pool.getNumActive());
        System.out.println("pool.getNumIdle() = " + pool.getNumIdle());
        pool.close();
    }
}
