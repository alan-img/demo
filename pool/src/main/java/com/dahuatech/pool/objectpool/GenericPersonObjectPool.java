package com.dahuatech.pool.objectpool;

import com.dahuatech.pool.bean.Person;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * <p>projectName: sourcecode</p>
 * <p>packageName: com.dahuatech.pool.demo.object</p>
 * <p>className: PersonObjectPool</p>
 * <p>date: 2023/6/4</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
public class GenericPersonObjectPool {
    private static final Logger log = LoggerFactory.getLogger(GenericPersonObjectPool.class);
    public static final int maxTotal = 10;
    public static final int minIdle = 0;
    public static final int maxIdle = 10;
    public final ObjectPool<Person> innerGenericPersonObjectPool;

    /**
     * 空参构造器
     */
    public GenericPersonObjectPool() {
        GenericObjectPoolConfig<Person> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxTotal(maxTotal);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxIdle(maxIdle);

        innerGenericPersonObjectPool = new GenericObjectPool<>(new BasePooledObjectFactory<Person>() {
            @Override
            public Person create() throws Exception {
                return new Person("alan", 23, "hangzhou");
            }

            @Override
            public PooledObject<Person> wrap(Person obj) {
                return new DefaultPooledObject<>(obj);
            }
        }, genericObjectPoolConfig);


        try {
            innerGenericPersonObjectPool.addObjects(maxTotal);
        } catch (Exception e) {
            log.warn("add object exception", e);
        }
    }

    /**
     * 带参构造器
     * @param maxTotal
     * @param minIdle
     * @param maxIdle
     */
    public GenericPersonObjectPool(int maxTotal, int minIdle, int maxIdle) {
        GenericObjectPoolConfig<Person> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxTotal(maxTotal);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxIdle(maxIdle);

        innerGenericPersonObjectPool = new GenericObjectPool<>(new BasePooledObjectFactory<Person>() {
            @Override
            public Person create() throws Exception {
                return new Person("alan", 23, "hangzhou");
            }

            @Override
            public PooledObject<Person> wrap(Person obj) {
                return new DefaultPooledObject<>(obj);
            }
        }, genericObjectPoolConfig);


        try {
            innerGenericPersonObjectPool.addObjects(maxTotal);
        } catch (Exception e) {
            log.warn("add object exception", e);
        }
    }

    /**
     * 获取对象
     * @return
     */
    public Person get() {
        try {
            return innerGenericPersonObjectPool.borrowObject();
        } catch (Exception e) {
            log.warn("borrow object exception", e);
            return null;
        }
    }

    /**
     * 归还对象
     * @param person
     */
    public void ret(Person person) {
        try {
            innerGenericPersonObjectPool.returnObject(person);
        } catch (Exception e) {
            log.warn("return object exception", e);
        }
    }

    /**
     * 获取正在使用的对象数
     * @return
     */
    public int getNumActive() {
        try {
            return innerGenericPersonObjectPool.getNumActive();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取空闲的对象数
     * @return
     */
    public int getNumIdle() {
        try {
            return innerGenericPersonObjectPool.getNumIdle();
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 关闭内部对象池
     */
    public void close() {
        if (Objects.nonNull(innerGenericPersonObjectPool)) {
            innerGenericPersonObjectPool.close();
        }
    }
}
