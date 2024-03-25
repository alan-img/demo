package com.dahuatech.jedis.utils

import lombok.extern.slf4j.Slf4j
import org.slf4j.bridge.SLF4JBridgeHandler
import org.slf4j.{Logger, LoggerFactory}
import redis.clients.jedis.{Jedis, JedisPoolConfig, JedisSentinelPool}

import java.util
import java.util.Properties

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.jedis.utils</p>
 * <p>className: RedisUtil</p>
 * <p>date: 2023/5/5</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

object RedisUtil {
  SLF4JBridgeHandler.removeHandlersForRootLogger()
  SLF4JBridgeHandler.install()

  private val logger: Logger = LoggerFactory.getLogger(getClass)
  private val jedisSentinelPool: JedisSentinelPool = getJedisSentinelPool()

  /**
   * 获取jedis池配置对象
   *
   * @param prop
   * @return jedis池配置对象
   */
  def getJedisPoolConfig(prop: Properties): JedisPoolConfig = {
    val jedisPoolConfig = new JedisPoolConfig()
    jedisPoolConfig.setMaxTotal(Integer.parseInt(prop.getProperty("redis.max.total", "8")))
    jedisPoolConfig.setMaxIdle(Integer.parseInt(prop.getProperty("redis.max.idle", "8")))

    jedisPoolConfig
  }

  /**
   * 获取jedis连接池对象
   *
   * @param prop
   * @return jedis连接池对象
   */
  def getJedisSentinelPool(properties: Properties = FileUtil.loadResourceAsProperties("redis.properties")): JedisSentinelPool = {
    val masterName: String = properties.getProperty("master.name", "mymaster")
    val sentinels = new util.HashSet[String]()
    sentinels.add(properties.getProperty("redis.sentinel.address", "hadoop102:26379"))
    val jedisPoolConfig: JedisPoolConfig = getJedisPoolConfig(properties)
    val timeout: Int = properties.getProperty("redis.timeout", "15000").toInt
    val password: String = properties.getProperty("redis.password", "root")
    val database: Int = properties.getProperty("redis.database", "11").toInt

    new JedisSentinelPool(
      masterName,
      sentinels,
      jedisPoolConfig,
      timeout,
      password,
      database
    )
  }

  /**
   * 获取jedis连接对象 重试2次 一共尝试获取3次 间隔1秒
   *
   * @param retryTimes
   * @return jedis连接对象
   */
  def getJedis(retryTimes: Int = 2): Jedis = {
    try {
      jedisSentinelPool.getResource
    } catch {
      case exp: Throwable => {
        Thread.sleep(1000L)

        if (retryTimes > 0) {
          getJedis(retryTimes - 1)
        } else {
          throw exp
        }
      }
    }
  }

  /**
   * 关闭jedis哨兵连接池 一般不调用此API 因为常驻进程不需要关闭连接池 离线任务会随着任务结束关闭连接池 之所以写上是为了逻辑完整
   * @param jedisSentinelPool
   */
  def closeJedisSentinelPool(jedisSentinelPool: JedisSentinelPool): Unit = {
    try
      if (jedisSentinelPool != null) jedisSentinelPool.close()
    catch {
      case exp: Exception => exp.printStackTrace()
    }
  }
}
