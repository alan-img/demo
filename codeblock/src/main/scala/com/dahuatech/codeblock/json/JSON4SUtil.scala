package com.dahuatech.codeblock.json

import org.json4s.Formats
import org.json4s.native.Serialization

import scala.collection.mutable.ArrayBuffer

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.json</p>
 * <p>className: JSON4SUtil</p>
 * <p>date: 2023/5/5</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

/*
        <dependency>
            <groupId>org.json4s</groupId>
            <artifactId>json4s-native_${scala.binary.version}</artifactId>
            <version>${json4s.native.version}</version>
        </dependency>
 */
object JSON4SUtil {
  implicit val formats = org.json4s.DefaultFormats

  // 将样例类对象转为json字符串
  def toJSONString(obj: Object): String = Serialization.write(obj)

  // 将json字符串转为样例类对象
  def parseObject[T](jsonString: String, clazz: Class[T])(implicit formats: Formats, mf: Manifest[T]): T = Serialization.read[T](jsonString)

  // 将json字符串转为样例类对象数组
  def parseArray[T](jsonString: String, clazz: Class[T])(implicit formats: Formats, mf: Manifest[T]): ArrayBuffer[T] = Serialization.read[ArrayBuffer[T]](jsonString)
}
