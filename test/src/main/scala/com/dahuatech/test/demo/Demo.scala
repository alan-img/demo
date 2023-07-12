package com.dahuatech.test.demo

import java.lang.reflect.Field
import scala.collection.mutable

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech</p>
 * <p>className: Demo</p>
 * <p>date: 2023/3/22</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
object Demo {
  implicit val formats = org.json4s.DefaultFormats

  def main(args: Array[String]): Unit = {

    // val buffer = "1,2,3".split(",")
    // println(buffer)
    // val seq = Seq("2").toBuffer
    // println((buffer.diff(seq)).toBuffer)

    // val runtime: Runtime = Runtime.getRuntime
    // val process: Process = runtime.exec(System.getenv("PWD").concat("/a.txt"))
    // val ret: Int = process.waitFor()
    // println(ret)
    // if (ret == 0) {
    //   println(ret)
    // }

    // println(System.getenv().keySet())
    // println(System.getenv("JAVA_HOME"))
    // println(System.getProperties.keySet())
    // println(System.getProperty("java.runtime.name"))

    // val arr: Array[Int] = Array(1, 2, 3, 4)
    // println(arr.zipWithIndex.drop(2).toBuffer)
    // println(arr.zipWithIndex.take(2).toBuffer)
    // println(arr.takeRight(2).toBuffer)
    // println(arr.splitAt(2)._1.toBuffer)
    // println(arr.splitAt(2)._2.toBuffer)
    // println(arr.dropWhile(_ > 2).toBuffer)
    // arr.zipWithIndex.init.foreach {
    //   case (ele, index) => println(s"ele: ${ele}, index: ${index}")
    // }

    // val map: mutable.HashMap[String, Int] = mutable.HashMap[String, Int]()
    // println(map)
    // val i: Int = map.getOrElseUpdate("a", 10)
    // println(map)
    // println(i)
    // map("a") = 100
    // map += ("b" -> 1000)
    // map.put("c", 10000)
    // map += (("d", 1000000))
    // println(map)

    // val arr: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4)
    // val strings: Array[Int] = arr.to[Array]
    // println(strings.toBuffer)

    // val tickets: ArrayBuffer[Int] = ArrayBuffer()
    // val threadPool: ExecutorService = Executors.newFixedThreadPool(2)
    // val countDownLatch = new CountDownLatch(2)
    //
    // for (i <- 0 until 2) {
    //   threadPool.submit(new Runnable {
    //     override def run(): Unit = {
    //       for (i <- 0 until 10) {
    //         // tickets.append(i)
    //         // tickets.synchronized(tickets.append(i))
    //         ArrayBuffer.synchronized(tickets.append(i))
    //         // Seq.synchronized(tickets.append(i))
    //       }
    //
    //       countDownLatch.countDown()
    //     }
    //   })
    // }
    //
    // countDownLatch.await()
    //
    // println(tickets.length)
    // println(tickets)
    // println(tickets.distinct.length)
    // println(tickets.distinct)
    //
    // threadPool.shutdown()


    // val arr: ArrayBuffer[ArrayBuffer[Int]] = ArrayBuffer(ArrayBuffer(1, 2), ArrayBuffer(3, 4))
    // val arrayBuffer: ArrayBuffer[Int] = arr.reduce(_ ++ _)
    // println(arrayBuffer.head)
    // println(arrayBuffer.tail)
    // println(arrayBuffer.init)
    // println(arrayBuffer.last)
    // println(arrayBuffer.take(2))
    // val ints: ArrayBuffer[Int] = arrayBuffer.drop(2)
    // println(ints)
    // arrayBuffer.splitAt()

    // val floats = new Array[Float](0)
    // println(floats.size)
    // println(floats.toBuffer)

    // val arr: Array[Int] = Array(1, 2, 3)
    // println(Array.getClass)
    //
    // println(arr.toBuffer)

    // val arr: ArrayBuffer[ArrayBuffer[Int]] = ArrayBuffer(ArrayBuffer(1, 2), ArrayBuffer(3, 4))
    // val ints: ArrayBuffer[Int] = arr.reduce {
    //   (a, b) =>
    //     a.append(b: _*)
    //     a
    // }
    // println(ints)
    //
    // val a = ArrayBuffer(1, 2)
    // val b = ArrayBuffer(1, 2)
    // a.append(b: _*)
    //
    // println(a)

    // val timer = new Timer()
    //
    // val timerTask: TimerTask = () -> {
    //   println("alan")
    // }

    // timer.schedule(timerTask, 1L)

    // val buffer: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)
    // buffer.foreach {
    //   x => println(x)
    // }
    //
    // buffer.grouped(2).foreach {
    //   buf =>
    //     println(buf)
    // }
    //
    // buffer.foreach {
    //   x => println(x)
    // }

    // val a: Array[String] = null
    //
    // if (Objects.isNull(a) || a.isEmpty) {
    //   print(s"lana: ${if (!a.isEmpty) a.isEmpty}")
    // }

    // val list: ListBuffer[Int] = display()
    // println(List().isEmpty)

    // val arr: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4)
    // arr.grouped(2).foreach {
    //   buf => {
    //     println(buf)
    //     println(buf.size)
    //   }
    // }

    // val faceRecord: FaceRecord = FaceRecord("alan", 23)
    // val gson = new Gson()
    // println(gson.toJson(faceRecord))

    // val arr =  ArrayBuffer("alan", "ajck")
    //
    // arr

    // val list = List(1, 2, 3)
    // println(list.length)
    // println(list(1))
    // println()
    //
    // new ListBuffer[Int]()
    // new ArrayBuffer[Int](10)
    // new Array[Int](10)

    // val listBuffer = ListBuffer(1, 2, 3)
    // println(listBuffer.length)
    // listBuffer(0) = 10
    // println(listBuffer(0))
    // listBuffer.remove()

    // val arr = ArrayBuffer("a")
    // arr.append("1")
    // arr.append("2")
    // arr.append("3")
    // arr.remove(0, 2)
    // println(arr)
    //
    // println(arr.length)

    // val a = Array(1, 2, 3)
    // a(0) = 1
    // a(1) = 2
    // println(a(1))
    // println(a.size)
    // println(a.length)
    // println(a.drop(1).length)
    // println(a.toBuffer)

    // val faceRecord: FaceRecord = FaceRecord("jack", 23)
    // println(JSON4SUtil.toJSONString(faceRecord))
    //
    // val record: FaceRecord = JSON4SUtil.parseObject("{\"name\":\"jack\",\"age\":23}", classOf[FaceRecord])
    // println(record)
    //
    // val record1 = JSON4SUtil.parseArray("[{\"name\":\"jack\",\"age\":23}, {\"name\":\"alan\",\"age\":24}]", classOf[FaceRecord])
    // println(record1)

    // val arrayBuffer = new ArrayBuffer[Int]()
    // arrayBuffer.append(1)
    // println(arrayBuffer(0))
    // println(arrayBuffer.apply(0))
    //
    // val obj: JSONObject = JSON.parseObject(
    //   """
    //     |{"name": "alan", "age": 23}
    //     |""".stripMargin)
    // println(JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue))
    //
    // val arr = new JSONArray()
    // for (i <- 0 to 5) {
    //   arr.add(obj)
    // }
    //
    // println(JSON.toJSONString(arr, SerializerFeature.DisableCircularReferenceDetect))

    // Array[Int](1, 2, 3).var

    // println(Array("alan").mkString(","))
    //
    // println(Map(1 -> 2))

    // val fis = new FileInputStream(new File("D:\\dev\\idea\\project\\demo\\streaming\\src\\main\\resources\\channel.txt"))
    // val reader = new InputStreamReader(fis)
    // val br = new BufferedReader(reader)
    // var line = br.readLine()
    // while (line != null) {
    //   println(line)
    //   line = br.readLine()
    // }

    // val file = new File("logback.xml")
    // println(file.getAbsolutePath)

    // case class Demo(var name: String, age: Int) {
    //   def this() {
    //     this("alan", 23)
    //   }
    // }
    //
    // val alan: Demo = Demo("alan", 23)
    // val jack: Demo = Demo()
    //
    // val alan1 = new Demo("alan", 2)
    // val jack1 = new Demo()

    // println(Demo.a)

    // Try {
    //   val a = 1 / 1
    // } match {
    //   case Success(_) => println("success")
    //   case Failure(exception) => println(exception)
    // }
    // print("alan")

    // val arr: Array[String] = Array("name=alan")
    // val map: Map[String, String] = arr.map {
    //   line => {
    //     val fields: Array[String] = line.split("=")
    //     (fields(0), fields(1))
    //   }
    // }.toMap[String, String]
    //
    // map.foreach {case (key, value) => println(key, value)}
    //
    // map.foreach {
    //   kv: (String, String) => println(kv._1)
    // }
    //
    // val prop = new Properties()

    // val buffer: mutable.Buffer[Int] = Array(1, 2, 3, 4).toBuffer.diff(Array(1,2,3,4).toBuffer)
    // buffer.append(null)
    // println(buffer.toString())
    //
    // println(null.toString)

    // println(ArrayBuffer().mkString(",") == "")

    // val obj = new JSONObject()
    // obj.put("age", 12)
    // obj.put("name", null)
    // obj.put(null, 10)
    // println(obj.getIntValue("age"))
    // println(obj.getIntValue("name"))
    // println(obj.getIntValue(null))

    // val okHttpClient: OkHttpClient = OkHttpClientFactory.getInstance().getOkHttpClient
    // val body: RequestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),
    //   """
    //     |
    //     |""".stripMargin)
    // val request: Request = new Request.Builder()
    //   .url("http://www.baidu.com")
    //   .post(body)
    //   .build()
    // val call: Call = okHttpClient.newCall(request)
    // val response: Response = call.execute()
    // println(response.request().url())
    // println(response.isSuccessful)
    // println(response.code())
    // println(response.body().string())

    // val obj = new JSONObject()
    // obj.put("name", "alan")
    // obj.put("age", 25)
    //
    // System.out.println(JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue))
    //
    // println(JSON.parseObject(JSON.toJSONString(obj, SerializerFeature.WriteMapNullValue), classOf[Person]))
  }
}
