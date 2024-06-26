package com.dahuatech.okhttp.demo

import com.dahuatech.okhttp.utils.{MediaTypeEnum, OkHttpClientUtil}
import com.squareup.okhttp.{MediaType, OkHttpClient, Request, RequestBody, Response, ResponseBody}

import java.net.{HttpURLConnection, URL}
import java.util.concurrent.{ExecutorService, Executors, TimeUnit}
import scala.util.control.Breaks
import scala.util.control.Breaks.break

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
object Demo {
  val okHttpClient: OkHttpClient = OkHttpClientUtil.getOkHttpClient

  def main(args: Array[String]): Unit = {

    println(okHttpClient.getConnectionPool.getConnectionCount)

    Breaks.breakable {
      while (true) {
        val request: Request = new Request.Builder()
          .get()
          .url("http://hadoop101:8888/get/12?name=alan")
          .addHeader("connection", "keep-alive")
          .build()
        val response: Response = okHttpClient.newCall(request).execute()

        println(response.code())
        println(response.message())
        TimeUnit.MILLISECONDS.sleep(50L)

        Breaks.break()
      }
    }
  }

  /**
   * 发送请求
   *
   * @return
   */
  def sendRequest(): Unit = {
    val requestBody: RequestBody = RequestBody.create(MediaType.parse(MediaTypeEnum.APPLICATION_JSON_CHARSET_UTF_8.getValue),
      "[{\"channel\": \"Fw61LVxMA1BHNH5LQAU3TQ\", \"gpX\": 120.1111, \"gpY\": 30.222}]")

    val request: Request = new Request.Builder()
      .url("http://localhost:8888/time/space/file")
      // .url("http://pc-offline-dossier-fix-0.pc-offline-dossier-fix.cloudspace.svc.cluster.local:9083/api/dossierfix/upload")
      // .get()
      .post(requestBody)
      .build()

    val response: Response = okHttpClient.newCall(request).execute()
    val responseBody: ResponseBody = response.body()

    println(response.code())
    println(response.isSuccessful)
    println(responseBody.string())
  }
}
