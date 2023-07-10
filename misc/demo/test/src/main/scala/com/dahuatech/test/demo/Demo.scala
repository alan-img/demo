package com.dahuatech.test.demo

import org.slf4j.LoggerFactory
import sun.nio.ch.DirectBuffer

import java.nio.ByteBuffer
import java.util.Scanner
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.demo</p>
 * <p>className: Demo</p>
 * <p>date: 2023/3/22</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */
object Demo {

  private val logger = LoggerFactory.getLogger(getClass)
  private val scanner = new Scanner(System.in)

  def main(args: Array[String]): Unit = {

    

    // val array: Array[Int] = Array(1, 2, 3)
    // println(array.getClass)
    // val arrayBuffer: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)
    // println(arrayBuffer.getClass)
    // val listBuffer: ListBuffer[Int] = ListBuffer(1, 2, 3)
    // println(listBuffer)

    // // 申请内存
    // val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * Integer.parseInt(args(0)))
    // scanner.nextInt()
    //
    // // 释放内存
    // byteBuffer.asInstanceOf[DirectBuffer].cleaner().clean()
    // scanner.nextInt()

    // val threadPool: ExecutorService = Executors.newFixedThreadPool(10)

    // val t1: Thread = new Thread(new Runnable {
    //   override def run(): Unit = {
    //     println(Thread.currentThread().isDaemon)
    //     for (i <- 0 to 10) {
    //       println(s"i = ${i}")
    //     }
    //   }
    // })
    // threadPool.submit(t1)

    // val future: Future[Int] = threadPool.submit(new Runnable {
    //   override def run(): Unit = {
    //     for (i <- 0 to 10) {
    //       println(s"i = ${i}")
    //     }
    //   }
    // }, 10)
    //
    // println(future.get())
    //
    // threadPool.shutdown()

    // t1.start()


    // println(scala.util.Properties.ScalaCompilerVersion)
    // println(scala.util.Properties.copyrightString)
    // println(scala.util.Properties.developmentVersion)
    // println(scala.util.Properties.versionString)
    // println(scala.util.Properties.releaseVersion)

    // val person: Person = new Person("alan", 23, null)
    // println(JSON.toJSONString(person, SerializerFeature.WriteMapNullValue))

    // val jsonStr = "{\"address\":null,\"age\":23,\"name\":\"alan\"}"
    // val jsonObj: JSONObject = JSON.parseObject(jsonStr)
    // println(JSON.toJSONString(jsonObj, SerializerFeature.WriteMapNullValue))
    // val p1: Person = JSON.parseObject(jsonStr, classOf[Person])
    // println(JSON.toJSONString(p1, SerializerFeature.WriteMapNullValue))
    //
    // val arrArrayStr = "[{\"address\":null,\"age\":23,\"name\":\"alan\"}, {\"address\":null,\"age\":23,\"name\":\"alan\"}]"
    // val jsonArr: JSONArray = JSON.parseArray(arrArrayStr)
    // println(JSON.toJSONString(jsonArr, SerializerFeature.WriteMapNullValue))
    // val pArr: util.List[Person] = JSON.parseArray(arrArrayStr, classOf[Person])
    // println(JSON.toJSONString(pArr, SerializerFeature.WriteMapNullValue))
  }
}
