package com.dahuatech.test.demo

import com.alibaba.fastjson.serializer.SerializerFeature
import com.alibaba.fastjson.{JSON, JSONObject}
import com.carrotsearch.sizeof.RamUsageEstimator
import com.dahuatech.test.bean.Person
import com.dahuatech.test.utils.JSON4SUtil
import javafx.beans.binding.ListBinding
import org.slf4j.{Logger, LoggerFactory}

import java.net.InetAddress
import java.util
import java.util.UUID
import java.util.concurrent.{CountDownLatch, ExecutorService, Executors}
import scala.collection.{immutable, mutable}
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.sys.SystemProperties

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
class CustomizeMap[K, V] extends mutable.HashMap[K, V] {

}
object Demo {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  implicit val formats = org.json4s.DefaultFormats



  def main(args: Array[String]): Unit = {

    val arr1: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)
    val arr2: ArrayBuffer[String] = ArrayBuffer("1", "2", "3", "4")
    println(arr1.zip(arr2))

    // val map: Map[Int, Int] = Map(1 -> 2)
    //
    // println(map(1))

    // println("alan".split(",").toBuffer)

    // val arrayBuffer = List(1, 2, 3)
    // arrayBuffer(0) = 10
    // arrayBuffer.update(0, 100)
    // val i: Int = arrayBuffer.apply(0)
    // println(i)
    // println(arrayBuffer(0))

    // val value: CustomizeMap[Int, Int] = new CustomizeMap[Int, Int]()
    //
    // value += 1 -> 2
    // println(value)

    // val arrayBuffer: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3)
    //
    // arrayBuffer.foreach{x: Int => println(x)}

    // val arrayBuffer: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4, 7, 6, 5)
    // println(arrayBuffer.sortWith((x, y) => x > y))
    // println(arrayBuffer.sorted)
    // println(arrayBuffer.sortBy(x => x))

    // val str: String = UUID.randomUUID().toString.replace("-", "")
    // println(str)


    // val arrayBuffer: ListBuffer[Int] = ListBuffer.empty[Int]
    // val service: ExecutorService = Executors.newFixedThreadPool(10)
    //
    // val countDownLatch = new CountDownLatch(10)
    // for (i <- 0 until 10) {
    //   service.execute(new Runnable {
    //     override def run(): Unit = {
    //       try {
    //         for (j <- 0 until 10) {
    //           // ArrayBuffer.synchronized(arrayBuffer.append(j))
    //           arrayBuffer.append(j)
    //         }
    //       } finally {
    //         countDownLatch.countDown()
    //       }
    //     }
    //   })
    // }
    // countDownLatch.await()
    //
    // println(arrayBuffer.length)
    // service.shutdown()

    // val ints: Array[Int] = Array(1, 2, 3)
    // val list1: List[Int] = List(1, 2, 3)
    // val ints1: Array[Int] = ints.map(x => x)
    // val ints2: List[Int] = list1.map(x => x)

    // val list = new util.ArrayList[String]()
    // list.add("a")
    // list.add("b")
    //
    // println(list)

    // object Enum extends Enumeration {
    //   val RED = Value(1, "red")
    //   val BLUE = Value(2, "blue")
    //   val YELLOW = Value(3, "yellow")
    // }

    // println(Enum.RED)
    // println(Enum.RED.id)
    // Enum.values.foreach(x => println(x.id))
    // Enum.values.foreach(x => println(x))
    // val values: Enum.ValueSet = Enum.values
    // println(values)
    // val set: Enum.ValueSet.type = Enum.ValueSet
    // println(set)

    // val a = new A("alan")
    // a.display(10)
    // println(a.age)
    // println(A.b)

    // val map: Map[Int, Array[String]] = Map((1, Array("a", "b", "c")), 2 -> Array("a", "b", "c"))

    // val ints: Seq[Int] = Seq(1, 2, 3, 4).take(10)
    // println(ints)

    // var map: Map[Int, Int] = Map[Int, Int]()
    // map += (1 -> 2)
    // map += ((1, 20))
    // map.foreach(
    //   (kv: (Int, Int)) => println(kv._1, kv._2)
    // )

    // println(1.hashCode())
    // println(-1.hashCode())
    // println("1".hashCode)
    // println("-1".hashCode)

    // println(Integer.MIN_VALUE)
    // println(Math.abs(Integer.MIN_VALUE))
    // println(Integer.MIN_VALUE.abs)
    // println(-12 % 3)

    // val arrayBuffer: ArrayBuffer[Int] = ArrayBuffer(1, 2, 3, 4, 5, 6, 7, 8)
    // val indices: Range = arrayBuffer.indices
    // println(indices)
    //
    // val ints: ArrayBuffer[Int] = arrayBuffer.slice(0, 3)
    // println(ints)

    // val map: mutable.Map[Int, Int] = mutable.Map[Int, Int]() + ((10, 20))
    // map(30) += 40
    // println(map)

    // map += ((1, 2))
    // map += 3 -> 4
    // var map1: Map[Int, Int] = Map[Int, Int]() + ((10, 20))
    // map1 += ((1, 2))
    // map1 += 3 -> 4
    // println(map)
    // println(map1)

    // println(logger.getClass)
    // println(logger.getClass.getName)
    // println(logger.getClass.getSimpleName)

    // val arr: ArrayBuffer[Int] = ArrayBuffer[Int]()
    // arr.append(0)
    // arr.append(1)
    // arr.append(2)
    //
    // println(RamUsageEstimator.sizeOf(arr))
    // println(RamUsageEstimator.sizeOf(arr) / arr.size)

    // val threadPool: ExecutorService = Executors.newFixedThreadPool(10)
    // threadPool.execute(new Runnable {
    //   override def run(): Unit = {
    //     throw new Exception("alan")
    //   }
    // })
    // threadPool.shutdown()
    //
    // println("sdfasdfasdfasdfasdfSd")

    // val jsonObject: JSONObject = JSON.parseObject(
    //   """
    //     |{"name": "alan", "age"
    //     |""".stripMargin)

    // try {
    //   val a = 1 / 0
    // } catch {
    //   case exp: Exception =>
    //     logger.error("output test", exp)
    //     throw new RuntimeException("output test1")
    // }


    // println(InetAddress.getLocalHost)
    // println(InetAddress.getLocalHost.getHostAddress)
    // println(System.getProperty("os.name"))
    // println(System.getenv("JAVA_HOME"))
    // println(getClass)
    // println(getClass.getName)
    // println(getClass.getSimpleName)

    // println("alan")

    // val opt: Option[Int] = if (false) Some(10) else None
    // println(opt.get)
    // println(opt.getOrElse(20))

    // var map: Map[Int, Int] = Map(1111 -> 1111)
    // map += ((1, 2))
    // map += (2 -> 3)
    // println(map)

    // println(ArrayBuffer(1, 1, 3).map(x => (x, x)).toMap[Int, Int])

    // val str =
    //   """
    //     |{"name": "jack", "arr": [{"name": "tom", "age": 23}, {"name": "adam", "age": 24}]}
    //     |""".stripMargin
    //
    // val obj: JSONObject = JSON.parseObject(str, classOf[JSONObject])
    // println(obj)
    // println(obj.getObject("arr", classOf[Array[JSONObject]]).toBuffer)

    // val obj = new JSONObject()
    // obj.put("name", "alan")
    // obj.put("age", 23)
    // obj.put("addr", null)

    // val jsonObj = JSON.toJSON(person).asInstanceOf[JSONObject]
    // println(jsonObj.toJSONString)

    // case class Person()
    //
    // val p = Person()
    //
    // println(p.getClass)
    // println(classOf[Person])
    // Class.forName("Person")
    // ClassLoader.getSystemClassLoader.loadClass("Person")

    // println(logger.isErrorEnabled())
    // println(logger.isWarnEnabled())
    // println(logger.isInfoEnabled())
    // println(logger.isDebugEnabled())

    // val s = "{\"ageGroup\":6}"
    // if (!s.contains("ageGroup")) {
    //   println("alan")
    // } else {
    //   println("jack")
    // }

    // val obj = new  JSONObject()
    // obj.put("alan", "")
    // println(obj.toJSONString)

    // val arr: ListBuffer[Int] = ListBuffer(1, 2, 3, 4, 5, 6)

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
