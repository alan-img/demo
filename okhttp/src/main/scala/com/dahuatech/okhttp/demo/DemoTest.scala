package com.dahuatech.okhttp.demo

import com.dahuatech.okhttp.demo.Demo.okHttpClient
import com.dahuatech.okhttp.utils.{MediaTypeEnum, OkHttpClientUtil}
import com.squareup.okhttp._

import java.util.concurrent.TimeUnit
import scala.util.control.Breaks

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.demo</p>
 * <p>className: Demo</p>
 * <p>date: 2023/4/1</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
object DemoTest {
  val okHttpClient: OkHttpClient = OkHttpClientUtil.getOkHttpClient

  def main(args: Array[String]): Unit = {

    val req = new Request.Builder()
      .get()
      .url("http://www.baidu.com")
      .build()
    val res = okHttpClient.newCall(req).execute()

    println(res.code())
    println(res.message())
    println(res.body().string())


  }

}
