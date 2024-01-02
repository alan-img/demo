package com.dahuatech.test.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dahuatech.test.bean.FaceDossier;
import com.dahuatech.test.bean.Human;
import com.dahuatech.test.bean.NamedThreadPool;
import com.dahuatech.test.exception.DemoException;
import lombok.val;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.collection.JavaConversions;
import scala.collection.JavaConverters;
import scala.collection.mutable.ArrayBuffer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech</p>
 * <p>className: Demo</p>
 * <p>date: 2023/3/20</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */

public class JavaDemo {
    private static Logger logger = LoggerFactory.getLogger(JavaDemo.class);
    private static ReentrantLock reentrantLock = new ReentrantLock();
    private static boolean flag = true;
    private static AtomicInteger balance = new AtomicInteger(10000);

    public static void main(String[] args) {
        // test1();

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10, new NamedThreadPool("first"));

        // threadPool

        threadPool.shutdown();
    }

    private static void test1() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10, new NamedThreadPool("first"));

        for (int i = 0; i < 10; i++) {
            threadPool.submit(() -> {
                logger.info("alan");
            });
        }

        threadPool.shutdown();
    }
}