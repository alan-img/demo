package com.dahuatech.kafka.streaming

import com.dahuatech.kafka.demo.KafkaParamConfig
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerConfig, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.kafka.streaming</p>
 * <p>className: SparkDemoProducer</p>
 * <p>date: 2023/6/20</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
object SparkDemoProducer {

  def main(args: Array[String]): Unit = {

    val prop: Properties = new Properties()
    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaParamConfig.BOOTSTRAP_SERVERS_CONFIG)
    prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    prop.put(ProducerConfig.BUFFER_MEMORY_CONFIG, KafkaParamConfig.BUFFER_MEMORY_CONFIG)
    prop.put(ProducerConfig.BATCH_SIZE_CONFIG, KafkaParamConfig.BATCH_SIZE_CONFIG)
    prop.put(ProducerConfig.LINGER_MS_CONFIG, KafkaParamConfig.LINGER_MS_CONFIG)
    prop.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, KafkaParamConfig.COMPRESSION_TYPE_CONFIG)
    prop.put(ProducerConfig.ACKS_CONFIG, KafkaParamConfig.ACKS_CONFIG)
    prop.put(ProducerConfig.RETRIES_CONFIG, KafkaParamConfig.RETRIES_CONFIG)

    val kafkaProducer: KafkaProducer[String, String] = new KafkaProducer[String, String](prop)

    try {
      for (id <- 0 until 100000) {
        kafkaProducer.send(
          new ProducerRecord[String, String](
            "first",
            id.toString,
            s"""
               |{"id":"29BA6ACE7A9427489C33DC59${id}","title":"体验课01","desp":"","tags":" ","duration":503,"category":"07AD1E11DBE6FDFC","image":"http://${id}.img.bokecc.com/comimage/0DD1F081022C163E/2016-03-09/29BA6ACE7A9427489C33DC5901307461-${id}.jpg","imageindex":0,"json":{"name":"alan","age": ${id}},"image-alternate":[{"index":0,"url":"http://2.img.bokecc.com/comimage/0DD1F081022C163E/2016-03-09/29BA6ACE7A9427489C33DC5901307461-0/0.jpg"},{"index":1,"url":"http://2.img.bokecc.com/comimage/0DD1F081022C163E/2016-03-09/29BA6ACE7A9427489C33DC5901307461-0/1.jpg"},{"index":2,"url":"http://2.img.bokecc.com/comimage/0DD1F081022C163E/2016-03-09/29BA6ACE7A9427489C33DC5901307461-0/2.jpg"},{"index":3,"url":"http://2.img.bokecc.com/comimage/0DD1F081022C163E/2016-03-09/29BA6ACE7A9427489C33DC5901307461-0/3.jpg"}]}
               |""".stripMargin
          )
        )
      }
    } catch {
      case exp: Exception => println(s"happen exception: ${exp.getMessage}")
    } finally {
      kafkaProducer.close()
    }

  }

}
