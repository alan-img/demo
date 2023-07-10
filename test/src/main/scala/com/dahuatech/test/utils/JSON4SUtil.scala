package com.dahuatech.test.utils

import com.dahuatech.test.bean.FaceRecord
import org.json4s.Formats
import org.json4s.native.Serialization
import org.junit.Test
import org.junit.experimental.theories.suppliers.TestedOn

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
object JSON4SUtil {
  implicit val formats = org.json4s.DefaultFormats

  def toJSONString(obj: Object): String = Serialization.write(obj)

  def parseObject[T](jsonString: String, clazz: Class[T])(implicit formats: Formats, mf: Manifest[T]): T = Serialization.read[T](jsonString)

  def parseArray[T](jsonString: String, clazz: Class[T])(implicit formats: Formats, mf: Manifest[T]): ArrayBuffer[T] = Serialization.read[ArrayBuffer[T]](jsonString)

  def main(args: Array[String]): Unit = {
    val faceRecord: FaceRecord = FaceRecord(101, Array(1.2f, 1.4f), "alan")
    println(JSON4SUtil.toJSONString(faceRecord))

    val record: FaceRecord = JSON4SUtil.parseObject("{\"name\":\"jack\",\"age\":23}", classOf[FaceRecord])
    println(record)

    val record1: ArrayBuffer[FaceRecord] = JSON4SUtil.parseArray("[{\"name\":\"jack\",\"age\":23}, {\"name\":\"alan\",\"age\":24}]", classOf[FaceRecord])
    println(record1)
  }
}
