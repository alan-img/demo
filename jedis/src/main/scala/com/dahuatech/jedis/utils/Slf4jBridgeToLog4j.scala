package com.dahuatech.jedis.utils

import org.slf4j
import org.slf4j.LoggerFactory
import org.slf4j.bridge.SLF4JBridgeHandler
import redis.clients.jedis.Jedis

import java.util.logging.Logger

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.jedis.utils</p>
 * <p>className: Slf4jBridgeToLog4j</p>
 * <p>date: 2023/5/5</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

/*
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>jul-to-slf4j</artifactId>
            <version>1.7.30</version>
        </dependency>
 */
object Slf4jBridgeToLog4j {
  /**
   * 背景：主要是jedis的日志采用jul实现，不是我们常用的slf4j门面的实现，为了统一特将jul桥接到slf4j
   * 局限性：这里需要在代码中执行如下两行代码，耦合性高，目前没有不清楚有没有更好的解决方案，待以后更新
   */
  // ############## 导入jul-to-slf4j包后必须在main函数中执行如下代码 才能确保桥接成功 ##############
  SLF4JBridgeHandler.removeHandlersForRootLogger()
  SLF4JBridgeHandler.install()
  // ###############################################################################################

  def main(args: Array[String]): Unit = {
    val jedis: Jedis = RedisUtil.getJedis()
    println(jedis.ping())
  }
}
