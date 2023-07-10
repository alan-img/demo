package com.dahuatech.codeblock.scala;

import scala.collection.JavaConversions;
import scala.collection.mutable.ArrayBuffer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.scala.demo</p>
 * <p>className: JavaUseScalaCollection</p>
 * <p>date: 2023/5/6</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */

/**
 * 注意事项:
 *  1.在java中直接使用scala集合的转换操作或者将scala集合toStream然后map、filter、foreach能方法报错
 *  如果必须要转换则有三种方式：
 *  1.1、使用new Function1等方法，也就是要啥给啥
 *  1.2、使用JavaConversions.bufferAsJavaList(arrayBuffer)转为Java集合然后使用java的Stream API配合Lambda操作
 *  注意这时，lambda操作符是 "->" 而不是scala中的 "=>"需注意
 *  1.3、封装一个Scala方法在Scala中操作房后返回回来
 */
public class JavaUseScalaCollection {
    /**
     * ArrayBuffer<Integer> arr = new ArrayBuffer<>();
     * List<Integer> list1 = JavaConversions.bufferAsJavaList(arr);
     * Collection<Integer> col1 = JavaConversions.asJavaCollection(arr);
     * List<Integer> list2 = JavaConverters.bufferAsJavaListConverter(arr).asJava();
     * Collection<Integer> col2 = JavaConverters.asJavaCollectionConverter(arr).asJavaCollection();
     */
    public static void main(String[] args) {
        // 在java中创建scala集合
        ArrayBuffer<Integer> arrayBuffer = new ArrayBuffer<>();
        // 将scala集合转换为java集合 这个要手动使用JavaConversions调用对应方法创建 在scala中是通过隐试转换完成的
        List<Integer> javaList = JavaConversions.bufferAsJavaList(arrayBuffer);
        // 通过java集合向scala集合中添加数据
        javaList.add(1);
        javaList.add(2);
        javaList.add(3);
        // 只有通过java集合添加以后才能通过scala集合的update和apply方法操作数据
        arrayBuffer.update(0, 10);
        System.out.println(arrayBuffer.apply(0));
        // 打印scala集合
        System.out.println(arrayBuffer); // ArrayBuffer(1, 2, 3)
    }

    /**
     * 在Java中实现mkString的功能
     */
    public static void mkString() {
        Integer[] arr = {1, 2, 3};
        String line = Arrays.stream(arr).map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(line); // 1,2,3
    }
}
