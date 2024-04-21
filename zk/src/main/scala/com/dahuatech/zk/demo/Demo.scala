package com.dahuatech.zk.demo

import com.dahuatech.zk.utils.ZKUtil
import com.sun.xml.internal.bind.api.impl.NameConverter.Standard
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.recipes.cache.{NodeCache, NodeCacheListener, PathChildrenCache, PathChildrenCacheEvent, PathChildrenCacheListener}
import org.apache.curator.framework.recipes.locks.InterProcessMutex
import org.apache.zookeeper.CreateMode

import java.nio.charset.StandardCharsets
import java.util.concurrent.{ExecutorService, Executors, TimeUnit}
import scala.collection.JavaConversions._
import scala.collection.JavaConverters

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
  val curatorFramework: CuratorFramework = ZKUtil.curatorFramework

  def main(args: Array[String]): Unit = {
    listenNodeDataChange()
  }

  def listenNodeDataChange(): Unit ={
    val nodeCache: NodeCache = new NodeCache(curatorFramework, "/test")

    nodeCache.getListenable.addListener(new NodeCacheListener {
      override def nodeChanged(): Unit = {
        println(new String(nodeCache.getCurrentData.getData, StandardCharsets.UTF_8))
      }
    })

    nodeCache.start()
    TimeUnit.DAYS.sleep(10)
    nodeCache.close()
  }

  def listenNodePathChange(): Unit ={
    val pathChildrenCache: PathChildrenCache = new PathChildrenCache(curatorFramework, "/test", true)

    pathChildrenCache.getListenable.addListener(new PathChildrenCacheListener {
      override def childEvent(client: CuratorFramework, event: PathChildrenCacheEvent): Unit = {
        if (PathChildrenCacheEvent.Type.CHILD_ADDED.eq(event.getType)) {
          println(s"child add: ${event.getData.getPath} -> ${new String(event.getData.getData, StandardCharsets.UTF_8)}")
        }

        if (PathChildrenCacheEvent.Type.CHILD_REMOVED.eq(event.getType)) {
          println(s"child remove: ${event.getData.getPath} -> ${new String(event.getData.getData, StandardCharsets.UTF_8)}")
        }
      }
    })

    pathChildrenCache.start()
    TimeUnit.DAYS.sleep(10)
    pathChildrenCache.close()
  }
}
