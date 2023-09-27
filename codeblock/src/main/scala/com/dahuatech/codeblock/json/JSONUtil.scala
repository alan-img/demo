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
 *    1.JSON字符串转为JSON对象或JSON数组
 *      1.1 解析对象时建议使用使用JSON.parseObject()
 *      1.2 解析数组时使用JSON.parseArray(jsonObj, classOf[JSONObject])
 *            1.2.1 建议优先加classOf[JSONObject]使用更清晰
 *            1.2.2 如果数组内的对象不是JSON对象而是普通类型如String 使用JSON.parseArray(jsonObj, classOf[String])
 *            1.2.3 不加classOf[JSONObject] 返回的是JSONArray类型 不推荐使用
 *
 *    2.JSON对象或JSON数组转为JSON字符串
 *      2.1 JSON字符串解析为JSON对象后在打印对象时都会丢掉"null"字段
 *            2.1.1 这个字段其实已经是这个对象的一个属性了 get时拿到的"null"值实际上就是它 只是打印时忽略了"null"字段而已
 *            2.1.2 转为字符串时设置SerializerFeature.WriteMapNullValue是可以打印出来的
 *      2.2 保留null属性使用JSON.toJSONString(jsonObj, SerializerFeature.WriteMapNullValue)
 *            2.2.1 这个在scala和java中均可使用 推荐使用这种方式
 *      2.3 不保留null字段 (很少使用，一般不推荐不保留null字段)
 *            2.3.1 在java代码中: JSON.toJSONString(jsonObj)
 *            2.3.2 在Scala代码中: jsonObject.toJSONString
 *
 *    3.将Java对象转为JSON对象
 *            3.1 使用JSON.toJSON(javaObj)
 *              JSONObject jsonObj = (JSONObject)JSON.toJSON(javaObj);
 *
 *    4.将Java对象转为JSON字符串
 *            4.1 使用JSON.toJSONString(javaObj))
 *              String jsonString = JSON.toJSONString(javaObj, SerializerFeature.WriteMapNullValue))
 *    5.将JSON字符串转为Java对象
 *            5.1 使用JSON.parseObject(jsonString, classOf[Human])
 *              Human human = JSON.parseObject(jsonString, classOf[Human])
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
