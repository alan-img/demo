package com.dahuatech.okhttp.demo

import com.dahuatech.okhttp.utils.OkHttpClientUtil
import com.squareup.okhttp.{MediaType, OkHttpClient, Request, RequestBody, Response, ResponseBody}

import java.util.concurrent.TimeUnit

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
  private val mediaType = "application/json;charset=utf-8"

  def main(args: Array[String]): Unit = {

    // okHttpClient
    val okHttpClient: OkHttpClient = OkHttpClientUtil.getOkHttpClient

    val requestBody: RequestBody = RequestBody.create(MediaType.parse(mediaType), "[{\"channel\": \"Fw61LVxMA1BHNH5LQAU3TQ\", \"gpX\": 120.1111, \"gpY\": 30.222}]")
    // request
    val request: Request = new Request.Builder()
      .url("http://localhost:8888/time/space/file")
      // .url("http://pc-offline-dossier-fix-0.pc-offline-dossier-fix.cloudspace.svc.cluster.local:9083/api/dossierfix/upload")
      .get()
      .post(requestBody)
      .build()

    // response
    val response: Response = okHttpClient.newCall(request).execute()
    val responseBody: ResponseBody = response.body()

    println(response.code())
    println(response.isSuccessful)
    println(responseBody.string())
  }

  /**
   * 发送请求
   *
   * @return
   */
  def sendRequest(): Response = {

    val okHttpClient: OkHttpClient = OkHttpClientUtil.getOkHttpClient
    val requestBody: RequestBody = RequestBody.create(MediaType.parse(mediaType), "[{\"channel\": \"Fw61LVxMA1BHNH5LQAU3TQ\", \"gpX\": 120.1111, \"gpY\": 30.222}]")
    val request: Request = new Request.Builder()
      .url("http://localhost:8888/time/space/file")
      .get()
      .post(requestBody)
      .build()

    TimeUnit.SECONDS.sleep(5)

    okHttpClient.newCall(request).execute()
  }
}
