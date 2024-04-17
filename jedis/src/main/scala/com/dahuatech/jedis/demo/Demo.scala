package com.dahuatech.jedis.demo

import com.dahuatech.jedis.utils.RedisUtil
import org.slf4j.{Logger, LoggerFactory}
import redis.clients.jedis.Jedis

import java.util.concurrent.{ExecutorService, Executors, TimeUnit}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.jedis.demo</p>
 * <p>className: Demo</p>
 * <p>date: 1/6/2024</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */

object Demo {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    val threadPool: ExecutorService = Executors.newFixedThreadPool(10)
    for (i <- 0 until 100) {
      threadPool.execute(new Runnable {
        override def run(): Unit = {
          val jedis: Jedis = RedisUtil.getJedis()
          Demo.synchronized {
            val age: Int = jedis.get("age").toInt
            jedis.set("age", (age - 1).toString)
          }
          jedis.close()
        }
      })
    }
    threadPool.shutdown()
  }
}
