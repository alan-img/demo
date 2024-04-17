package com.dahuatech.pool.config;

/**
 * <p>projectName: sourcecode</p>
 * <p>packageName: com.dahuatech.pool.config</p>
 * <p>className: GenericObjectPoolConstant</p>
 * <p>date: 2023/6/4</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
public class GenericObjectPoolConstant {
    /**
     * genericObjectPoolConfig.setMaxTotal(10);
     * genericObjectPoolConfig.setMinIdle(0);
     * genericObjectPoolConfig.setMaxIdle(10);
     * genericObjectPoolConfig.setMaxWaitMillis(10000);
     */
    public static final int maxTotal = 10;
    public static final int minIdle = 0;
    public static final int maxIdle = 10;
    public static final Long maxWaitSeconds = 5L;

}
