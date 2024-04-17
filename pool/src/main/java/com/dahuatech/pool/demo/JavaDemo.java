package com.dahuatech.pool.demo;

import com.dahuatech.pool.bean.Person;
import com.dahuatech.pool.objectpool.PersonObjectPool;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

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
    public static void main(String[] args) throws InterruptedException {
        PersonObjectPool personObjectPool = new PersonObjectPool();
        while (true) {
            Person person = personObjectPool.borrowObject();
            System.out.println(person);
            // personObjectPool.returnObject(person);
            TimeUnit.SECONDS.sleep(1);
            System.out.println("pool.getNumActive() = " + personObjectPool.getNumActive());
            System.out.println("pool.getNumIdle() = " + personObjectPool.getNumIdle());
        }
    }
}
