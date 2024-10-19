package com.dahuatech.pool.demo;

import com.dahuatech.pool.bean.Person;
import com.dahuatech.pool.objectpool.GenericPersonObjectPool;

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

        GenericPersonObjectPool genericPersonObjectPool = new GenericPersonObjectPool();
        Person person = genericPersonObjectPool.get();
        System.out.println(person);
        System.out.println(genericPersonObjectPool.innerGenericPersonObjectPool.getNumActive());
        genericPersonObjectPool.ret(person);
        System.out.println(genericPersonObjectPool.innerGenericPersonObjectPool.getNumIdle());

    }
}
