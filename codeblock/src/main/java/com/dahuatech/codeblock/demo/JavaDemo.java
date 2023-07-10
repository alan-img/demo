package com.dahuatech.codeblock.demo;

import com.dahuatech.codeblock.pool.CustomizedObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.collection.JavaConversions;
import scala.collection.mutable.ListBuffer;
import sun.misc.Cleaner;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.demo</p>
 * <p>className: JavaDemo</p>
 * <p>date: 2023/5/4</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */

public class JavaDemo {
    private static final Logger logger = LoggerFactory.getLogger(JavaDemo.class);

    public static void main(String[] args) throws Exception {

        // Exception exp = new Exception("alan");
        //
        // logger.info("alan {}", "jack");

        // ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 500);
        // Thread.sleep(30000L);
        // System.out.println("clear memory");
        // byteBuffer.clear();
        // System.gc();
        // Thread.sleep(40000L);

        // ListBuffer<Integer> listBuffer = new ListBuffer<>();
        // List<Integer> list = JavaConversions.bufferAsJavaList(listBuffer);
        // list.add(1);
        // list.add(2);
        // list.add(3);
        // System.out.println(listBuffer);
        // HashMap<Integer, ListBuffer<Integer>> hashMap = new HashMap<>();
        // hashMap.put(1, listBuffer);
        // int a = (int)hashMap.get(1).apply(2);
        // System.out.println(a);

        // String s = null;
        //
        // try {
        //     s.getBytes();
        // } catch (Exception exp) {
        //     System.out.println(exp);
        //     System.out.println(exp.getMessage());
        // }
        //
        // HashMap<Integer, Integer> map = new HashMap<>();
        // System.out.println(map.get(1));

        // GenericObjectPoolConfig<Integer> config = new GenericObjectPoolConfig<>();
        // config.setMaxIdle(10);
        // config.setMinIdle(5);
        // config.setMaxTotal(10);
        // GenericObjectPool<Integer> objectPool = new GenericObjectPool<>(new CustomizedObjectPool(), config);
        // objectPool.addObjects(10);
        //
        // Integer a = objectPool.borrowObject();
        // System.out.println(a);
        //
        //
        // objectPool.returnObject(a);

        // HashMap<Integer, Integer> hashMap = new HashMap<>();
        // hashMap.put(1, 1);
        // hashMap.put(2, 2);
        // hashMap.put(3, 3);
        // hashMap.put(4, 1);
        // hashMap.put(5, 2);
        // hashMap.put(6, 3);
        //
        // HashSet<Integer> integers = new HashSet<>(hashMap.values());
        // System.out.println(integers);

        // System.out.println(hashMap.containsKey(1));
        // System.out.println(hashMap.containsKey(10));
        // hashMap.get("alan");
    }
}
