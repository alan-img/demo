package com.dahuatech.jna.demo

import com.sun.jna.{Memory, Native, Pointer}

import java.util.Scanner
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech</p>
 * <p>className: Demo</p>
 * <p>date: 2023/3/27</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
object Demo {
  val scanner = new Scanner(System.in)

  def main(args: Array[String]): Unit = {



    // val num: Int = 1024 * 1024 * args(0).toInt
    // // val num: Int = 1024 * 1024 * 10
    // val memory = new Memory(num)
    // // 使用 注意JNA向内存写入数据才占内存 不写入即使new Memory()也不占堆外内存
    // val bytes = new Array[Byte](1024)
    // for (i <- 0 until num by 1024) {
    //   memory.write(i, bytes, 0, 1024)
    // }
    // scanner.nextInt()
    // // 释放
    // Native.free(Pointer.nativeValue(memory))
    // Pointer.nativeValue(memory, 0L)
    // scanner.nextInt()
  }
}
