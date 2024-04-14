package com.dahuatech.kafka.demo

import org.apache.kafka.clients.producer._
import org.slf4j.{Logger, LoggerFactory}

import java.util.Random
import java.util.concurrent.TimeUnit
import scala.collection.immutable

object DemoProducer {
  private val logger: Logger = LoggerFactory.getLogger(getClass)
  private val topicName: String = "first"
  val kafkaProducer: KafkaProducer[String, String] = new KafkaProducer[String, String](KafkaParamConfig.getProducerProperties())

  def generateRandomString(length: Int): String = {
    val chars: immutable.IndexedSeq[Char] = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9') ++ Seq('/', '+')
    val random = new Random
    (1 to length).map(_ => chars(random.nextInt(chars.length))).mkString
  }

  // /**
  //  * 异步精确发送一次
  //  */
  // def asyncExactlyOnceSendRecord(): Unit = {
  //   kafkaProducer.initTransactions()
  //   kafkaProducer.beginTransaction()
  //
  //   try {
  //     for (i <- 0 until 10) {
  //       kafkaProducer.send(
  //         new ProducerRecord[String, String](topicName, "key_" + i, generateRandomString(10))
  //       )
  //     }
  //     kafkaProducer.commitTransaction() // 提交事务
  //   } catch {
  //     case exp: Exception => {
  //       logger.error("kafka producer send record failed", exp)
  //       kafkaProducer.abortTransaction() // 发送异常 终止事务
  //     }
  //   } finally {
  //     kafkaProducer.close()
  //   }
  // }
  //
  // /**
  //  * 同步精确发送一次 不推荐使用同步 同步发送性能太差了
  //  */
  // def syncExactlyOnceSendRecord(): Unit = {
  //   kafkaProducer.initTransactions()
  //   kafkaProducer.beginTransaction()
  //
  //   try {
  //     for (_ <- 0 until 10) {
  //       kafkaProducer.send(
  //         new ProducerRecord[String, String](topicName, generateRandomString(10), generateRandomString(512))
  //       )
  //     }
  //     kafkaProducer.commitTransaction() // 提交事务
  //   } catch {
  //     case exp: Exception => {
  //       logger.error("kafka producer send record failed", exp)
  //       kafkaProducer.abortTransaction() // 发送异常 终止事务
  //     }
  //   } finally {
  //     kafkaProducer.close()
  //   }
  // }

  def main(args: Array[String]): Unit = {
    while (true) {
      TimeUnit.MILLISECONDS.sleep(args(0).toLong)
      kafkaProducer.send(
        new ProducerRecord[String, String](topicName, "{\"featureData\":\"====\",\"gender\":2,\"extRecordSource\":1,\"roll\":0,\"fringe\":0,\"completeness\":1,\"recordId\":\"510703030013100003210120210222121523437120663712\",\"rightEyeCover\":0.9986891746520996,\"noseCover\":0.00002557138577685691,\"rightCheekCover\":0.006148375570774078,\"saturation\":0.2728027701377869,\"faceRight\":556,\"faceLeft\":519,\"detectionScore\":53,\"faceWidth\":34,\"faceTop\":49,\"pitch\":0,\"channelId\":\"OV9PFi9sA1CNBJSDR8P6KC\",\"capTime\":1613967324000,\"browCover\":0.01200886815786362,\"featureId\":1363703790544228353,\"channelCode\":\"OV9PFi9sA1CNBJSDR8P6KC\",\"mask\":0,\"leftEyeCover\":0.9968937635421753,\"leftCheekCover\":0.002195094246417284,\"illumination\":103,\"faceBottom\":89,\"qeScore\":53,\"confidence\":99,\"alignScore\":100,\"extRecordId\":\"510703030013100003210120210222121523437120163712\",\"yaw\":-20,\"imgUrl\":\"4f4f992458bd0314-f5ba-237e-3f5b-141a00000000_3008989_1613996139_305233.jpg\",\"relatedPersonCount\":1,\"faceImgUrl\":\"4f4f992458bd0314-f5ba-237e-3f5b-141a00000000_3007532_1613996139_1954.jpg\",\"chinCover\":0.009738578461110592,\"clarityness\":0.7159087657928467,\"mouthCover\":0.005254136398434639,\"channelName\":\"unknown\",\"isDriver\":0,\"age\":33,\"isAvailableGps\":1,\"bodiesInfo\":[{\"bag3Reliability\":0,\"personBottom\":485,\"hasErrorDetect\":0,\"coatTexture\":\"4\",\"coatStyleReliability\":99,\"hasHeadReliability\":99,\"recordId\":\"510703030013100003210120210222121523437120163718\",\"personLeft\":661,\"uniformStyle\":2,\"personTop\":86,\"vehicleType\":1,\"hasHead\":1,\"personRight\":803,\"coatColor\":\"1\",\"ageGroup\":3,\"hasRaincoat\":0,\"trousersStyle\":\"1\",\"hairStyle\":0,\"hasPicInfrared\":0,\"vehicleTypeReliability\":99,\"hasVehicle\":0,\"disappearFrameNum\":16059330,\"genderReliability\":98,\"isAvailableGps\":1,\"featureData\":\"====\",\"ageGroupReliability\":97,\"coatStyle\":\"99\",\"isNoncomplete\":0,\"hasDownBody\":1,\"gender\":2,\"capStyle\":\"99\",\"extRecordSource\":5,\"uniformStyleReliability\":100,\"trousersStyleReliability\":79,\"targetImgUrl\":\"4f4f992458bd0314-f5ba-237e-3f5b-141a00000000_3007538_1613996139_10027.jpg\",\"hasDownBodyReliability\":99,\"hairStyleReliability\":98,\"trousersColor\":\"5\",\"channelId\":\"OV9PFi9sA1CNBJSDR8P6KC\",\"capTime\":1613967332000,\"featureId\":1363704169499729920,\"channelCode\":\"OV9PFi9sA1CNBJSDR8P6KC\",\"appearFrameNum\":16059177,\"posture\":1,\"qeScore\":89,\"bag2Reliability\":0,\"coatTextureReliability\":99,\"extRecordId\":\"510703030013100003210120210222121523437120663712\",\"imgUrl\":\"584510a8fd5b03d9-31ad-dc0b-5fff-76d700000000_3008995_1613996139_290123.jpg\",\"capStyleReliability\":98,\"postureReliability\":99,\"bag1Reliability\":0,\"channelName\":\"unknown\",\"trousersColorReliability\":79,\"coatColorReliability\":59}]}")
      )
    }

    kafkaProducer.close()
  }
}
