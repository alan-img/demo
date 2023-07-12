package com.dahuatech.codeblock.misc

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
