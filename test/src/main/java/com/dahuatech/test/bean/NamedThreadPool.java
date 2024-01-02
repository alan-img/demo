package com.dahuatech.test.bean;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.test.bean</p>
 * <p>className: NamedThreadPool</p>
 * <p>date: 12/17/2023</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */

public class NamedThreadPool implements ThreadFactory {
    private String threadName;
    private AtomicInteger threadId = new AtomicInteger(0);

    public NamedThreadPool(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, threadName + "-" + threadId.getAndIncrement());
    }
}
