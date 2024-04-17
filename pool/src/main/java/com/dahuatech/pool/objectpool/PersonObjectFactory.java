package com.dahuatech.pool.objectpool;

import com.dahuatech.pool.bean.Person;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * <p>projectName: sourcecode</p>
 * <p>packageName: com.dahuatech.pool.object</p>
 * <p>className: PersonObjectFactory</p>
 * <p>date: 2023/6/4</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
public class PersonObjectFactory extends BasePooledObjectFactory<Person> {
    @Override
    public Person create() {
        System.out.println("create person...");
        return new Person("alan", 23, "ahut");
    }

    @Override
    public PooledObject<Person> wrap(Person person) {
        System.out.println("wrap persin...");
        return new DefaultPooledObject<>(person);
    }

    @Override
    public void destroyObject(PooledObject<Person> p) throws Exception {
        System.out.println("destroy object ...");
        super.destroyObject(p);
    }
}
