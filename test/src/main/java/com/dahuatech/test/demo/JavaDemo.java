package com.dahuatech.test.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import java.util.*;
import java.util.concurrent.*;
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

    public static void main(String[] args) throws InterruptedException {

        // HashMap<Integer, Integer> map = new HashMap<>();
        // map.put(1, 2);
        // map.put(2, 3);
        // map.put(3, 4);
        //
        // for (Integer value : map.values()) {
        //     System.out.println("value = " + value);
        // }

        // ArrayList<Integer> list = new ArrayList<>();
        // list.add(1);
        // list.add(2);
        // list.add(3);
        //
        // ArrayList<Integer> list1 = new ArrayList<>();
        // list1.add(2);
        // list1.add(3);
        //
        // list.removeAll(list1);
        // System.out.println(list);

        // ArrayBuffer<ArrayBuffer<String>> arr = new ArrayBuffer<>();
        // Demo.main(arr);

        // ArrayBuffer<Integer> arr = new ArrayBuffer<>();
        // List<Integer> list1 = JavaConversions.bufferAsJavaList(arr);
        // Collection<Integer> col1 = JavaConversions.asJavaCollection(arr);
        // List<Integer> list2 = JavaConverters.bufferAsJavaListConverter(arr).asJava();
        // Collection<Integer> col2 = JavaConverters.asJavaCollectionConverter(arr).asJavaCollection();

        // Thread thread = new Thread(() -> {
        //     try {
        //         Thread.sleep(10000L);
        //     } catch (InterruptedException e) {
        //         throw new RuntimeException(e);
        //     }
        // });
        //
        // thread.join();
        // thread.start();

        // FutureTask<Integer> ft = new FutureTask<>(() -> null);
        //
        // Map<String, FutureTask<Integer>> map = new HashMap<>();
        // map.get(0).get()

        // ArrayList<Integer> list = new ArrayList<>();
        // assert CollectionUtils.isNotEmpty(list);
        //
        // try {
        //     Objects.requireNonNull(null, "alan");
        // } catch (Exception e) {
        //     logger.error("happen exception", e);
        // }

        // String[] arr = {"aa", "bb"};
        //
        // System.out.println(String.join("、", arr));

        // ArrayBuffer<Integer> b1 = new ArrayBuffer<>();
        // b1.$plus$eq(1);
        // b1.$plus$eq(2);
        //
        // ArrayBuffer<Integer> b2 = new ArrayBuffer<>();
        // b2.$plus$eq(3);
        // b2.$plus$eq(4);
        //
        // b1.append(b2);
        //
        // System.out.println(b1);

        // HashMap<String, String> hashMap = new HashMap<>();
        // hashMap.put("a", "aa");
        // hashMap.put("b", "bb");
        // hashMap.put("c", "cc");
        // System.out.println(hashMap.containsKey("a"));

        // Executors.newFixedThreadPool(10, r -> {
        //     Thread thread = new Thread(r);
        //     thread.setName("alan");
        //
        //     return thread;
        // });

        // String json = "{\n" +
        //         "  \"age\": 31,\n" +
        //         "  \"alignmentScore\": 100.0,\n" +
        //         "  \"angle\": [\n" +
        //         "    -40,\n" +
        //         "    20,\n" +
        //         "    0\n" +
        //         "  ],\n" +
        //         "  \"byteSize\": 0,\n" +
        //         "  \"detectionScore\": 100.0,\n" +
        //         "  \"faceQs\": {\n" +
        //         "    \"brow\": 0.0,\n" +
        //         "    \"byteSize\": 0,\n" +
        //         "    \"chinContour\": 0.0,\n" +
        //         "    \"clarityness\": 0.0,\n" +
        //         "    \"completeness\": 0,\n" +
        //         "    \"illumination\": 0,\n" +
        //         "    \"leftCheek\": 0.0,\n" +
        //         "    \"leftEye\": 0.0,\n" +
        //         "    \"mouth\": 0.0,\n" +
        //         "    \"nose\": 0.0,\n" +
        //         "    \"reserved\": [\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0,\n" +
        //         "      0\n" +
        //         "    ],\n" +
        //         "    \"rightCheek\": 0.0,\n" +
        //         "    \"rightEye\": 0.0,\n" +
        //         "    \"saturation\": 0.0,\n" +
        //         "    \"type\": 0\n" +
        //         "  },\n" +
        //         "  \"fringe\": 0,\n" +
        //         "  \"gender\": 0,\n" +
        //         "  \"isDriver\": 0,\n" +
        //         "  \"mask\": 0,\n" +
        //         "  \"qeScore\": 66.0,\n" +
        //         "  \"rect\": {\n" +
        //         "    \"lr\": {\n" +
        //         "      \"x\": 219,\n" +
        //         "      \"y\": 200\n" +
        //         "    },\n" +
        //         "    \"ul\": {\n" +
        //         "      \"x\": 114,\n" +
        //         "      \"y\": 99\n" +
        //         "    }\n" +
        //         "  },\n" +
        //         "  \"reserved\": [\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0,\n" +
        //         "    0\n" +
        //         "  ],\n" +
        //         "  \"reservedInt8\": \"AAAA\",\n" +
        //         "  \"type\": 0\n" +
        //         "}";
        //
        // JSONObject jsonObject = JSON.parseObject(json);
        // System.out.println(jsonObject);

        // System.out.println(System.getenv().keySet().size());
        // for (String key : System.getenv().keySet()) {
        //     System.out.println(key + " -> " + System.getenv().get(key));
        // }
        //
        // System.out.println(System.getProperties().size());
        // System.out.println("===================================");
        // for (Object key : System.getProperties().keySet()) {
        //     System.out.println(key + " -> " + System.getProperties().get(key));
        // }

        // ExecutorService threadPool = Executors.newFixedThreadPool(2);
        // Thread t1 = new Thread(() -> {
        //     for (int i = 0; i < 10; i++) {
        //         System.out.println("t1");
        //         logger.warn("i = {}", i);
        //         try {
        //             Thread.sleep(1000L);
        //         } catch (InterruptedException e) {
        //             throw new RuntimeException(e);
        //         }
        //     }
        // });
        // threadPool.submit(t1);
        //
        // Thread t2 = new Thread(() -> {
        //     for (int i = 0; i < 10; i++) {
        //         System.out.println("t2");
        //         logger.info("i = {}", i);
        //         try {
        //             Thread.sleep(1000L);
        //         } catch (InterruptedException e) {
        //             throw new RuntimeException(e);
        //         }
        //     }
        // });
        // threadPool.submit(t2);
        //
        // threadPool.shutdown();

        // Integer[] arr = {1, 2, 3};
        // String line = Arrays.stream(arr).map(String::valueOf).collect(Collectors.joining(","));
        // System.out.println(line);

        // List<String> list = Arrays.asList("1,2,3".split(","));
        // ArrayList<String> list1 = new ArrayList<>();
        // list1.addAll(list);
        // System.out.println(list);

        // ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        // threadPool.scheduleWithFixedDelay(() -> System.out.println("alan"), 0, 1, TimeUnit.SECONDS);

        // Timer timer = new Timer();
        // timer.schedule(new TimerTask() {
        //     @Override
        //     public void run() {
        //         System.out.println("alan");
        //         System.out.println(Thread.currentThread().getName());
        //     }
        // }, 0L, 1000L);
        //
        // Thread.sleep(5000L);
        // timer.cancel();
        // timer.purge();

        // ArrayBuffer<Integer> arr = new ArrayBuffer<>(10);
        // List<Integer> list = JavaConversions.bufferAsJavaList(arr);
        // list.add(0);
        // list.add(1);
        // list.add(2);
        // System.out.println(arr);
        //
        // Collections.swap(list, 0, list.size() - 1);
        // System.out.println(arr);

        // ListBuffer<Object> buffer = Demo.display();
        // // JavaConversions
        // JavaConversions.bufferAsJavaList(buffer).stream().forEach(x -> {
        //     Integer a = ((Integer) x);
        //     System.out.println(a.shortValue());
        // });


        // buffer.toStream().foreach(x -> {
        //     System.out.println("alan");
        //     System.out.println(x);
        //     return x;
        // });
        // buffer.update(0, 1000);
        // System.out.println(buffer.apply(0));

        // ListBuffer<FaceRecord> buffer = Demo.show();

        // JavaConverters.asJavaCollectionConverter(buffer).asJavaCollection().stream().filter(x -> x.featureId() == 1).forEach(System.out::println);

        //
        // JavaConversions.bufferAsJavaList(buffer).stream().filter()

        // buffer.foreach(x -> x);

        // int i = buffer.toStream().filter(x -> x.featureId() == 1).head().featureId();
        // System.out.println(i);

        // List<Object> col = JavaConversions.bufferAsJavaList(buffer).stream().filter(x -> (Integer) x == 1).collect(Collectors.toList());
        // System.out.println(col);


        // buffer.toStream().filter(x -> (Integer) x == 1);
        // buffer.foreach(x -> x);
        // System.out.println(buffer.apply(0));

        // HashMap<String, String> hashMap = new HashMap<>();
        // hashMap.put("jack", "jack-value");
        // System.out.println(hashMap.getOrDefault("alan", hashMap.get("jack")));

        // ArrayList<Integer> listBuffer = new ArrayList<>();
        // Integer integer = listBuffer.stream().filter(x -> x == 0).collect(Collectors.toList()).get(0);
        // System.out.println(integer);

        // ArrayList<Integer> arr = new ArrayList<>();
        // Stream<Integer> stream = arr.stream();
        // arr.add(1);
        // arr.add(2);
        //
        // Collections.swap(arr, 0, 1);
        // System.out.println(arr);

        // ArrayBuffer<String> arr = new ArrayBuffer<>();
        // Collection<String> col = JavaConverters.asJavaCollectionConverter(arr).asJavaCollection();
        // System.out.println(col.getClass());
        // col.stream().forEach(x -> System.out.println(x));
        // arr.filter(x -> x == "aln").head()

        // JavaConversions.asJavaCollection();
        // JavaConverters.asJavaCollectionConverter()

        // ArrayList<Integer> arr = new ArrayList<Integer>();
        // arr.add(1);
        // arr.add(2);
        // arr.add(3);
        // Stream<Integer> integerStream = arr.stream().filter(x -> x == 1);
        // System.out.println(integerStream.collect(Collectors.toList()).get(1));

        // ArrayBuffer<String> array = Demo.main(new String[10]);
        // Iterator<String> iter = array.toIterator();
        // while (iter.hasNext()) {
        //     System.out.println(iter.next());
        // }
        //
        // System.out.println();
        // for (String s : JavaConversions.seqAsJavaList(array)) {
        //     System.out.println(s);
        // }
        //
        // List<String> list = JavaConversions.mutableSeqAsJavaList(array);

        // System.out.println(System.getProperty("user.dir"));

        // A a = new A();
        // a.start();
        // for (; ; ) {
        //     synchronized (Person.class) {
        //         System.out.println(Thread.currentThread().getName());
        //         System.out.println(a.isFlag());
        //         if (a.isFlag()) {
        //             System.out.println("有点东西");
        //         }
        //         try {
        //             Thread.sleep(100L);
        //         } catch (Exception exp) {
        //             exp.printStackTrace();
        //         }
        //     }
        // }

        // char c = 'a';
        // System.out.println(c);

        // String filename = "body_region_info.csv";
        //
        // System.out.println(filename == "body_region_info.csv");

        // HashMap<String, String> map = new HashMap<>();
        // map.put("1", "2");
        // map.put("3", "4");
        // System.out.println(map);

        // ArrayList<String> list = new ArrayList<>();
        //
        // try {
        //      throw new ArrayIndexOutOfBoundsException("alan");
        // } catch (ArrayIndexOutOfBoundsException exp) {
        //     System.out.println(exp);
        // }
        //
        // System.out.println(list);

        // ArrayList<Integer> list = new ArrayList<>();
        // list.add(1);
        // list.add(1);
        // list.add(2);
        // list.add(3);
        // list.add(5);
        // list.add(6);
        // list.add(7);

        // List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
        // System.out.println(collect);

        // Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        // Integer[] array = Arrays.copyOfRange(arr, 1, arr.length);
        // System.out.println(Arrays.toString(array));

        // String a = "alan-jack";
        // String replace = a.replace("alan", "tom");
        // System.out.println(replace);
        // System.out.println(a);

        // int[] arr = new int[10];
        // Arrays.fill(arr, 10);
        // System.out.println(Arrays.toString(arr));

        // HashMap<String, Object> hashMap = new HashMap<>();
        // hashMap.put("name", "alan");
        // System.out.println(hashMap.get("name"));
        // System.out.println(hashMap.getOrDefault("name1", "jack"));

        // HashMap<String, Person> hashMap = new HashMap<>();
        // hashMap.put("1001", new Person("alan1", 28));
        // hashMap.put("1002", new Person("alan2", 29));
        // hashMap.put("1003", new Person("alan3", 30));
        // hashMap.put("1004", new Person("alan4", 31));
        //
        // ArrayList<String> list = new ArrayList<>();
        // list.add("1001");
        // list.add("1002");
        //
        // list.forEach(s -> hashMap.remove(s));
        //
        // hashMap.forEach((a, b) -> System.out.println(b));

        // String s = "[{\"channel\": \"Fw61LVxMA1BHNH5LQAU3TQ\",\"gpX\": 120.1111,\"gpY\": 30.222}]";
        // List<JSONObject> arr = JSON.parseArray(s, JSONObject.class);
        // System.out.println(arr.size());
        // System.out.println(arr.get(0).getDouble("gpX"));

        // JSONArray arr = new JSONArray();
        // for (int i = 0; i < 10; i++) {
        //     JSONObject obj = new JSONObject();
        //     obj.put("name" + i, i);
        //     arr.add(obj);
        // }
        // System.out.println(arr);
    }
}
