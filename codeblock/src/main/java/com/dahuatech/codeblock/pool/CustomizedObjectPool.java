package com.dahuatech.codeblock.pool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.pool</p>
 * <p>className: CustomizedObjectPool</p>
 * <p>date: 2023/5/16</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @description
 * @since JDK8.0
 */

public class CustomizedObjectPool extends BasePooledObjectFactory<Integer> {
    @Override
    public Integer create() throws Exception {
        return new Integer(10);
    }

    @Override
    public PooledObject<Integer> wrap(Integer obj) {
        return null;
    }
}
