package com.dahuatech.pool.objectpool;

import com.dahuatech.pool.bean.Person;
import com.dahuatech.pool.config.GenericObjectPoolConstant;
import org.apache.commons.pool2.ObjectPool;
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
public class PersonObjectPool {
    private static final Logger logger = LoggerFactory.getLogger(PersonObjectPool.class);
    private final ObjectPool<Person> objectPool;

    public PersonObjectPool() {
        GenericObjectPoolConfig<Person> genericObjectPoolConfig = new GenericObjectPoolConfig<>();
        genericObjectPoolConfig.setMaxTotal(GenericObjectPoolConstant.maxTotal);
        genericObjectPoolConfig.setMinIdle(GenericObjectPoolConstant.minIdle);
        genericObjectPoolConfig.setMaxIdle(GenericObjectPoolConstant.maxIdle);
        genericObjectPoolConfig.setMaxWaitMillis(GenericObjectPoolConstant.maxWaitMillis);

        objectPool = new GenericObjectPool<>(new PersonObjectFactory(),
                genericObjectPoolConfig);

        try {
            objectPool.addObjects(GenericObjectPoolConstant.maxTotal);
        } catch (Exception e) {
            logger.warn("add object exception", e);
        }
    }

    public Person get() {
        try {
            return objectPool.borrowObject();
        } catch (Exception e) {
            logger.warn("borrow object exception", e);
            return null;
        }
    }

    public void ret(Person person) {
        try {
            objectPool.returnObject(person);
        } catch (Exception e) {
            logger.warn("return object exception", e);
        }
    }

    public int getNumActive() {
        try {
            return objectPool.getNumActive();
        } catch (Exception e) {
            return 0;
        }
    }

    public int getNumIdle() {
        try {
            return objectPool.getNumIdle();
        } catch (Exception e) {
            return 0;
        }
    }

    public void close() {
        if (Objects.nonNull(objectPool)) {
            objectPool.close();
        }
    }
}
