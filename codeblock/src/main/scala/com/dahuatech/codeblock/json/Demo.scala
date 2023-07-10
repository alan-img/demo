package com.dahuatech.codeblock.json

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import com.alibaba.fastjson.serializer.SerializerFeature
import com.dahuatech.codeblock.sql.Person

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.json</p>
 * <p>className: Demo</p>
 * <p>date: 2023/5/5</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

object Demo {
  def main(args: Array[String]): Unit = {
    val obj: JSONObject = JSON.parseObject(
      """
        |{"name": "alan", "age": 23, "address": null}
        |""".stripMargin)
    // 打印出空对象
    println(JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue))

    val arr = new JSONArray()
    for (i <- 0 to 2) {
      arr.add(obj)
    }
    // json数组引用多个相同的json对象，打印出现异常问题
    println(JSON.toJSONString(arr, SerializerFeature.DisableCircularReferenceDetect))

    println(JSONUtil.toJSONObject(Person("alan", 23)))
  }
}
