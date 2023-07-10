package com.dahuatech.codeblock.json

import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.{JSON, JSONObject}
import org.slf4j.{Logger, LoggerFactory}

import java.util
import java.io.{ByteArrayOutputStream, InputStream}
import java.nio.charset.StandardCharsets

/*
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>${fastjson.version}</version>
        </dependency>
 */
/*
 *  使用建议:
 *    1.JSON字符串转为JSON对象
 *      1.1 字符串解析为对象后在打印对象时都会丢掉null字段 这个字段其实已经是这个对象的一个属性了 只是打印时忽略了null字段而已
 *      转为字符串时设置SerializerFeature.WriteMapNullValue是可以打印出来的
 *      1.2 解析对象时建议使用使用JSON.parseObject()
 *      1.3 解析数组时使用JSON.parseArray(jsonObj, classOf[JSONObject])
 *      不加classOf[JSONObject]返回的是JSONArray类型 优先加classOf[JSONObject]使用更清晰
 *      1.4 如果数组内的对象不是JSON而是普通类型如String 使用JSON.parseArray(jsonObj, classOf[String])
 *    2.JSON对象转为JSON字符串
 *       2.1 不保留null属性直接jsonObj.toString
 *       2.2 保留null属性使用JSON.toJSONString(jsonObj, SerializerFeature.WriteMapNullValue)
 *       2.3 JSON.toJSONString(jsonObj)在scala中是错误用法 在java中正常
 *    3.将Java对象转为JSON对象
 *       3.1 使用JSON.toJSON(javaObj)
 *        JSONObject jsonObj = (JSONObject)JSON.toJSON(javaObj);
 */
object JSONUtil {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  /**
   * 将Java对象转为JSON对象
   *
   * @param javaObj
   * @return JSON对象
   */
  def toJSONObject(javaObj: Object): JSONObject = JSON.toJSON(javaObj).asInstanceOf[JSONObject]

  /**
   * JSON字符串转为JSON数组
   *
   * @param jsonString
   * @return JSON数组
   */
  def parseArray(jsonString: String): util.List[JSONObject] = JSON.parseArray(jsonString, classOf[JSONObject])

  /**
   * JSON字符串转为JSON对象
   *
   * @param jsonString
   * @return JSON对象
   */
  def parseObject(jsonString: String): JSONObject = JSON.parseObject(jsonString)

  /**
   * JSON对象转为JSON字符串
   *
   * @param jsonObj
   * @return JSON字符串
   */
  def toJSONString(jsonObj: JSONObject): String = JSON.toJSONString(jsonObj, SerializerFeature.WriteMapNullValue)

  /**
   * 将JSON文件转化为JSON对象
   *
   * @param filename
   * @return
   */
  def loadFileAsJSON(filename: String): JSONObject = {

    var jsonObject: JSONObject = null

    try {
      val is: InputStream = ClassLoader.getSystemClassLoader.getResourceAsStream(filename)
      val fileContent: String = readInputStreamToString(is)
      jsonObject = JSON.parseObject(fileContent)

      logger.info("successful load application config")
    } catch {
      case _: Throwable => logger.info("load config file failed")
    }

    jsonObject

  }

  /**
   * 读流并转化为字符串
   *
   * @param is
   * @return
   */
  def readInputStreamToString(is: InputStream): String = {

    var byteArrayOutputStream: ByteArrayOutputStream = null

    try {
      byteArrayOutputStream = new ByteArrayOutputStream()

      var len = 0
      val buf = new Array[Byte](1024)
      len = is.read(buf)
      while (len != -1) {
        byteArrayOutputStream.write(buf, 0, len)
        len = is.read(buf)
      }

    } finally {
      if (is != null) is.close()
      if (byteArrayOutputStream != null) byteArrayOutputStream.close()
    }

    new String(byteArrayOutputStream.toByteArray, StandardCharsets.UTF_8)
  }
}
