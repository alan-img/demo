package com.dahuatech.test.demo

import org.slf4j.{Logger, LoggerFactory}

//import scala.collection.immutable.HashMap
//import scala.collection.parallel.immutable

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech</p>
 * <p>className: Demo</p>
 * <p>date: 2023/3/22</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

/*
JVM参数：
-Xms300m
-Xmx300m
-XX:SurvivorRatio=8
-XX:MetaspaceSize=100m
-XX:MaxDirectMemorySize=100m
-XX:+UseGCLogFileRotation
-XX:NumberOfGCLogFiles=10
-XX:GCLogFileSize=100m
-Xloggc:D:\dev\idea\project\demo\test\gc.log
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-XX:+PrintGCDateStamps
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=D:\dev\idea\project\demo\test\heap.hprof
-Dfile.encoding=UTF-8
 */
object Demo {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {

    test()

  }

  def test(): Unit = {
    println("alan")
  }

}