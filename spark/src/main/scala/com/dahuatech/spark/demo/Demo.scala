package com.dahuatech.spark.demo

import com.dahuatech.okhttp.utils.OkHttpClientUtil
import com.dahuatech.spark.bean.SingleRecordPartitioner
import com.dahuatech.spark.utils.SparkUtil
import com.squareup.okhttp._
import org.apache.commons.io.output.ByteArrayOutputStream
import org.apache.spark.{Partitioner, SparkContext}
import org.jline.utils.InputStreamReader

import java.io.{BufferedInputStream, BufferedReader, FileInputStream, FileReader, OutputStreamWriter}
import java.util.concurrent.TimeUnit

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.spark.demo</p>
 * <p>className: Demo</p>
 * <p>date: 2023/3/28</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
object Demo {
  private val mediaType = "application/json;charset=utf-8"

  def main(args: Array[String]): Unit = {

    val sparkContext: SparkContext = SparkUtil.getLocalSparkSession().sparkContext
    val list: List[Int] = List(1, 2, 3, 4)

    val myPartitioner: SingleRecordPartitioner = SingleRecordPartitioner(4)
    sparkContext.makeRDD(list).zipWithIndex().map(tp => (tp._2, tp._1)).partitionBy(myPartitioner).map(_._2).mapPartitions {
      iter => {
        iter.foreach {
          num =>
            println(s"send: ${num}")
            val response: Response = sendRequest()
            println(response.body().string())
        }

        println("alan")
        println()
        iter
      }
    }
  }

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