package com.dahuatech.zk.demo

import com.dahuatech.zk.utils.ZKUtil
import com.sun.xml.internal.bind.api.impl.NameConverter.Standard
import org.apache.curator.framework.CuratorFramework
import org.apache.curator.framework.imps.CuratorFrameworkState
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
    // zk的监听县城时守护线程 当所有非守护线程全部运行结束时 JVM会将所有的守护线程退出 最终退出JVM
    // listenNodeDataChange()
    listenNodePathChange()
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
    // nodeCache.close() // 一般不会主动关闭，除非进程退出
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

        if (PathChildrenCacheEvent.Type.CHILD_UPDATED.eq(event.getType)) {
          println(s"child update: ${event.getData.getPath} -> ${new String(event.getData.getData, StandardCharsets.UTF_8)}")
        }
      }
    })

    pathChildrenCache.start()
    TimeUnit.DAYS.sleep(10)
    // pathChildrenCache.close() // 一般不会主动关闭，除非进程退出
  }
}
