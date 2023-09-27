package com.dahuatech.zk.utils

import com.dahuatech.zk.utils.ZKUtil.curatorFramework
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.state.{ConnectionState, ConnectionStateListener}
import org.apache.zookeeper.KeeperException

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.zk.utils</p>
 * <p>className: Demo</p>
 * <p>date: 2023/5/5</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

object Demo {
  def main(args: Array[String]): Unit = {
    println(ZKUtil.curatorFramework)
    try {
      curatorFramework.delete().forPath("/aaaaaaaaaaa")
    } catch {
      case exception: KeeperException.NoNodeException => println("alan")
    }
  }
}