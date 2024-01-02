package com.dahuatech.hdfs.utils

import java.util.Random
import scala.collection.immutable

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.hdfs.utils</p>
 * <p>className: MISCUtil</p>
 * <p>date: 1/1/2024</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */

object MISCUtil {
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
}