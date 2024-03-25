package com.dahuatech.zk.utils

import org.apache.curator.framework.{CuratorFramework, CuratorFrameworkFactory}
import org.apache.curator.retry.RetryNTimes
import org.apache.zookeeper.CreateMode
import org.apache.zookeeper.data.Stat

import java.util
import scala.collection.JavaConversions._

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.zk.utils</p>
 * <p>className: ZKUtil</p>
 * <p>date: 2023/5/5</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

object ZKUtil {
  // 不建议使用CuratorFrameworkFactory.newClient()方法创建CuratorFramework连接对象
  // 主要因为使用构建器方法可以提供显的方法设置相关配置
  val curatorFramework: CuratorFramework = CuratorFrameworkFactory.builder()
    // 连接字符串可提供多个并以"ip1:port1,ip2:port2,ip3:port3"的方式填写
    .connectString("hadoop101:2181,hadoop102:2181,hadoop103:2181")
    .sessionTimeoutMs(3000)
    .connectionTimeoutMs(1500)
    .retryPolicy(new RetryNTimes(3, 3000))
    .build()

  // 必须先启动 CuratorFramework连接对象才可以使用
  curatorFramework.start()

  /**
   * 以下都是小功能，直接可以复制使用，所以就不封装方法使用了
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {

    // 新增节点 默认创建永久节点 不能创建多级节点 返回值是创建的节点路径
    curatorFramework.create().forPath("/streaming/a", "alan".getBytes())

    // 创建临时节点
    curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/streaming/b", "".getBytes())

    // 创建永久序列节点
    for (i <- 1 to 3) {
      curatorFramework.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath(s"/streaming/${i}", "".getBytes())
    }

    // 删除节点
    curatorFramework.delete().forPath("/streaming/a")

    // 获取一个节点的值
    val bytes: Array[Byte] = curatorFramework.getData.forPath("/streaming/a")
    println(new String(bytes, "UTF-8"))

    // 获取一个路径下的所有子节点
    val subNodeList: util.List[String] = curatorFramework.getChildren.forPath("/streaming")
    // 显示指定转换 这里后续没有用到 只是展示使用方式 这里需要导入import scala.collection.JavaConverters._
    // 导入import scala.collection.JavaConversions._可以不显示的写asScala转换方法 但是这个对象在scala低版本废弃了 不建议使用
    subNodeList.toList
    subNodeList.foreach {
      subNodeName => println(subNodeName)
    }

    // 设置节点的值
    curatorFramework.setData().forPath("/streaming/a", "".getBytes())

    // 返回非null表示存在
    val stat: Stat = curatorFramework.checkExists().forPath("/streaming/20000000003")
    println(stat)

    curatorFramework.close()

  }
}
