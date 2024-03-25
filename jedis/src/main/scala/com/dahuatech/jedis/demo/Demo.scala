package com.dahuatech.jedis.demo

import com.dahuatech.jedis.utils.RedisUtil
import redis.clients.jedis.Jedis

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
  def main(args: Array[String]): Unit = {
    val jedis: Jedis = RedisUtil.getJedis()
    for (i <- 0 until 1000000) {
      jedis.del(i.toString)
    }
  }
}
