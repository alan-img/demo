package com.dahuatech.pool.demo;

import com.dahuatech.pool.bean.Person;
import com.dahuatech.pool.objectpool.PersonObjectPool;

import java.util.Scanner;

/**
 * <p>projectName: sourcecode</p>
 * <p>packageName: com.dahuatech.pool.demo</p>
 * <p>className: JavaDemo</p>
 * <p>date: 2023/6/4</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */

public class JavaDemo {
    public static void main(String[] args) {
        PersonObjectPool personObjectPool = new PersonObjectPool();
        Person person = personObjectPool.get();
        person = personObjectPool.get();
        person = personObjectPool.get();
        person = personObjectPool.get();
        System.out.println("personObjectPool.getNumActive() = " + personObjectPool.getNumActive());
        System.out.println("personObjectPool.getNumIdle() = " + personObjectPool.getNumIdle());
        personObjectPool.close();
    }
}
