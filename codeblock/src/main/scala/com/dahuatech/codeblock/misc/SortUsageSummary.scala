package com.dahuatech.codeblock.misc

import java.util.Random
import scala.collection.immutable
import scala.collection.mutable.ArrayBuffer

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.misc</p>
 * <p>className: SortUsageSummary</p>
 * <p>date: 2023/7/10</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

object SortUsageSummary {
  /**
   * 生成指定长度的随机字符串 非BASE64字符串 因为BASE64字符串"="只会出现在末尾
   *
   * @param length
   * @return
   */
  def generateRandomString(length: Int): String = {
    val chars: immutable.IndexedSeq[Char] = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9') ++ Seq('/', '+')
    val random = new Random
    (1 to length).map(_ => chars(random.nextInt(chars.length))).mkString
  }

  def main(args: Array[String]): Unit = {
    val arr: Array[Int] = Array(1, 2, 3)
    // 降序
    println(arr.sorted(Ordering[Int].reverse).toBuffer)
    // 降序
    println(arr.sortBy(x => x)(Ordering[Int].reverse).toBuffer)
    // 降序
    println(arr.sortWith(_ > _).toBuffer)
  }
}
