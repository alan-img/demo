package com.dahuatech.codeblock.directmemory

import sun.nio.ch.DirectBuffer

import java.nio.ByteBuffer
import scala.io.StdIn

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.directmemory</p>
 * <p>className: Demo</p>
 * <p>date: 2023/5/29</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 * @description
 */

object Demo {
  def main(args: Array[String]): Unit = {
    val MB: Int = 1024 * 1024 * Integer.parseInt(args(0))
    // 申请直接内存(堆外内存)
    val byteBuffer: ByteBuffer = ByteBuffer.allocate(MB)
    StdIn.readInt()
    // 释放直接内存
    freeDirectByteBuffer(byteBuffer)
    StdIn.readInt()
  }

  def freeDirectByteBuffer(byteBuffer: ByteBuffer): Unit = {
    if (byteBuffer.isDirect) {
      byteBuffer.asInstanceOf[DirectBuffer].cleaner().clean()
    }
  }
}
