package com.dahuatech.scala.demo;

import scala.collection.JavaConversions;
import scala.collection.mutable.ArrayBuffer;

import java.util.List;

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

public class JavaUseScalaCollection {
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
}
