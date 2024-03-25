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

  def generalTest(): Unit ={
    curatorFramework.create().forPath("/test1", "10".getBytes())
    curatorFramework.delete().forPath("/test1")
    val bytes: Array[Byte] = curatorFramework.getData.forPath("/test1")
    println(new String(bytes, StandardCharsets.UTF_8))

    curatorFramework.getChildren.forPath("/test1").foreach(println)
    curatorFramework.setData().forPath("/test1", "1111".getBytes())

    curatorFramework.checkExists().forPath("/test1")
  }

  def listenNodeDataChange(): Unit ={
    val nodeCache: NodeCache = new NodeCache(curatorFramework, "/test1")

    nodeCache.getListenable.addListener(new NodeCacheListener {
      override def nodeChanged(): Unit = {
        println(new String(nodeCache.getCurrentData.getData, StandardCharsets.UTF_8))
      }
    })

    nodeCache.start()
    TimeUnit.DAYS.sleep(1)
    nodeCache.close()
  }

  def listenNodePathChange(): Unit ={
    val pathChildrenCache: PathChildrenCache = new PathChildrenCache(curatorFramework, "/servers", true)

    pathChildrenCache.getListenable.addListener(new PathChildrenCacheListener {
      override def childEvent(client: CuratorFramework, event: PathChildrenCacheEvent): Unit = {
        if (PathChildrenCacheEvent.Type.CHILD_ADDED.eq(event.getType)) {
          println("child add -> " + new String(event.getData.getData, StandardCharsets.UTF_8))
          println(event.getData.getPath)
        }

        if (PathChildrenCacheEvent.Type.CHILD_REMOVED.eq(event.getType)) {
          println("child remove -> " + new String(event.getData.getData, StandardCharsets.UTF_8))
          println(event.getData.getPath)
        }
      }
    })

    pathChildrenCache.start()
    TimeUnit.DAYS.sleep(1)
    pathChildrenCache.close()
  }

  def serverDynamicOnlineOfflinePerception(): Unit ={
    val threadPool: ExecutorService = Executors.newFixedThreadPool(10)
    threadPool.execute(new Runnable {
      override def run(): Unit = {
        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/servers/svc1", "svc1".getBytes())
        TimeUnit.SECONDS.sleep(10)
        curatorFramework.delete().forPath("/servers/svc1")
      }
    })

    threadPool.execute(new Runnable {
      override def run(): Unit = {
        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/servers/svc2", "svc2".getBytes())
        TimeUnit.SECONDS.sleep(300)
      }
    })

    threadPool.execute(new Runnable {
      override def run(): Unit = {
        curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath("/servers/svc3", "svc3".getBytes())
        TimeUnit.SECONDS.sleep(300)
      }
    })

    threadPool.execute(new Runnable {
      override def run(): Unit = {
        listenNodePathChange()
      }
    })
    TimeUnit.SECONDS.sleep(300)
    threadPool.shutdown()
  }

  def main(args: Array[String]): Unit = {

  }
}
