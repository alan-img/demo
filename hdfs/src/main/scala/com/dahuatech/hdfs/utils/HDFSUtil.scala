package com.dahuatech.hdfs.utils

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs._
import org.apache.hadoop.io.IOUtils

import java.net.URI
import scala.collection.mutable.ArrayBuffer

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.hdfs.utils</p>
 * <p>className: HDFSUtil</p>
 * <p>date: 2023/5/5</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
object HDFSUtil {
  /**
   * FileSystem对象
   */
  private val fs: FileSystem = getFileSystem()

  /**
   * 获取FileSystem对象
   *
   * @return FileSystem对象
   */
  def getFileSystem(): FileSystem = FileSystem.get(URI.create("hdfs:///"), new Configuration(), "root")

  /**
   * 递归获取一个目录下所有文件的字符串全路径
   *
   * @param sourceFullPath
   * @param targetFullPaths
   */
  def getFileFullPathByDir(sourceFullPath: String, targetFullPaths: ArrayBuffer[String]): Unit = {
    val path = new Path(sourceFullPath)
    if (fs.exists(path)) {
      val fileStatuses: Array[FileStatus] = fs.listStatus(path)
      fileStatuses.foreach {
        fileStatus =>
          if (fileStatus.isFile)
            targetFullPaths.append(fileStatus.getPath.toString)
          else
            getFileFullPathByDir(fileStatus.getPath.toString, targetFullPaths)
      }
    }
  }

  /**
   * 以下都是小功能，直接可以复制使用，所以就不封装方法使用了
   *
   * @param args
   */
  def main(args: Array[String]): Unit = {

    val fs: FileSystem = getFileSystem()

    // 创建文件夹 可创建多级
    fs.mkdirs(new Path("/a/b/c"))

    // 创建文件
    fs.createNewFile(new Path("/a/b/c/d.txt"))

    // 判断文件或文件夹是否存在
    val isExist: Boolean = fs.exists(new Path("/jdk-8u202-linux-x64.tar.gz"))
    println(isExist)

    // 下载文件 支持重命名 可配置删不删除源文件 默认不删
    fs.copyToLocalFile(new Path("/jdk-8u202-linux-x64.tar.gz"), new Path("file:///D:/dev/idea/project/bigdata/data/demo.tar.gz"))

    // 上传文件 路径需要使用Path对象表示 可配置删不删除源文件 默认不删
    fs.copyFromLocalFile(new Path("file:///D:/dev/idea/project/bigdata/data/demo.tar.gz"), new Path("/demo1.tar.gz"))

    // 重命名文件同时也可移动位置 路径不同即表示移动位置
    fs.rename(new Path("/demo1.tar.gz"), new Path("/demo.tar.gz"))
    fs.rename(new Path("/demo.tar.gz"), new Path("/tmp/demo.tar.gz"))

    // 删除文件或文件夹 如果是文件夹且不是最后一级则需要设置递归删除为true
    fs.delete(new Path("/a"), true)
    fs.delete(new Path("/tmp/demo.tar.gz"), false)

    // 通过流复制文件
    val fis: FSDataInputStream = fs.open(new Path("/jdk-8u202-linux-x64.tar.gz"))
    val fos: FSDataOutputStream = fs.create(new Path("/demo.tar.gz"))
    IOUtils.copyBytes(fis, fos, 81920)

    // 获取文件或目录下（所有文件）的文件状态
    val fileStatuses: Array[FileStatus] = fs.listStatus(new Path("hdfs:///jdk-8u202-linux-x64.tar.gz"))
    fileStatuses.foreach {
      fileStatus =>
        println(fileStatus)
        // 通过FileStatus获取Path对象
        val path: Path = fileStatus.getPath
        // 打印全路径 和下面的主要区别是类型不同 一个是Path类型 一个是String类型 需要String类型就要toString
        println(path)
        // 打印全路径
        println(path.toString)
        // 打印最后一级路径
        println(path.getName)

        // 通过FileStatus对象可以获取很多关于文件的属性
        fileStatus.getPermission
        fileStatus.getOwner
        fileStatus.getGroup
        fileStatus.getLen
        fileStatus.getModificationTime
        fileStatus.getReplication
        fileStatus.getBlockSize

        // 判断是否是文件
        if (fileStatus.isFile) {
          println("is file")
        } else {
          println("is directory")
        }
    }

    // Array[FileStatus]转Array[Path]
    val paths: Array[Path] = FileUtil.stat2Paths(fileStatuses)

    // 通过Path获取FileStatus
    paths.foreach {
      path =>
        val fileStatus: FileStatus = fs.getFileStatus(path)
        println(fileStatus)
    }
  }
}
