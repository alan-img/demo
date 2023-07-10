package com.dahuatech.test.demo;

import com.dahuatech.test.bean.Singleton;
import org.apache.commons.collections.CollectionUtils;
import org.junit.experimental.theories.Theories;
import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.bean</p>
 * <p>className: JavaDemo</p>
 * <p>date: 2023/3/22</p>
 *
 * @author qinjiawei(alan)
 * @version 1.0.0
 * @since JDK8.0
 */
public class JavaDemo {
    private static ExecutorService threadPool;

    public static void main(String[] args) {

        ArrayList<Object> list = new ArrayList<>();
        assert CollectionUtils.isNotEmpty(list): "list is empty";

        // HashMap<String, Integer> map = new HashMap<>(10);
        //
        // new HashSet()

        // threadPool = Executors.newFixedThreadPool(10);
        // threadPool.submit(new Runnable() {
        //     @Override
        //     public void run() {
        //         for (int i = 0; i < 10; i++) {
        //             System.out.println("i = " + i);
        //             if (i == 5) {
        //                 // throw new RuntimeException("aan");
        //                 System.exit(0);
        //             }
        //         }
        //     }
        // });
        //
        // try {
        //     Thread.sleep(2000L);
        // } catch (InterruptedException e) {
        //     throw new RuntimeException(e);
        // }
        // System.out.println("main thread");
        //
        // threadPool.shutdown();

        // Error alan = new OutOfMemoryError("alan");
        // try {
        //     throw alan;
        // } catch (Error e) {
        //     System.out.println("alan");
        // }
        // System.out.println("jack");

        // threadPool = Executors.newFixedThreadPool(10);
        // threadPool.submit(() -> {
        //     for (int j = 0; j < 10; j++) {
        //         System.out.println("j = " + j);
        //     }
        // });
        // Random random = new Random();
        // FutureTask<Integer> futureTask = new FutureTask<>(() -> random.nextInt());
        // threadPool.submit(futureTask);
        // System.out.println(futureTask.get());
        // threadPool.shutdown();
    }
}