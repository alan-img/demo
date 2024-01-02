package com.dahuatech.hdfs.demo

import com.dahuatech.hdfs.utils.MISCUtil
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.permission.FsPermission
import org.apache.hadoop.fs.{FSDataOutputStream, FileStatus, FileSystem, FileUtil, LocatedFileStatus, Path, RemoteIterator}
import org.apache.hadoop.security.UserGroupInformation

import java.net.URL
import java.util
import scala.collection.Iterator.empty.hasNext
import scala.util.Random
import scala.util.control.Breaks

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.demo</p>
 * <p>className: HDFSUtil</p>
 * <p>date: 2023/3/15</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
object Demo {
  UserGroupInformation.setLoginUser(UserGroupInformation.createRemoteUser("root"))
  val configuration: Configuration = new Configuration()
  configuration.addResource("core-site.xml")
  // configuration.set("dfs.replication", "3")
  val fileSystem: FileSystem = FileSystem.get(configuration)

  def main(args: Array[String]): Unit = {



    // val fsDataOutputStream: FSDataOutputStream = fileSystem.create(new Path("/test.txt"))
    // Breaks.breakable({
    //   while (true) {
    //     fsDataOutputStream.writeBytes(MISCUtil.generateRandomString(10))
    //     println(fsDataOutputStream.getPos)
    //     fsDataOutputStream.flush()
    //     if (fsDataOutputStream.getPos == 1024 * 1024) {
    //       Breaks.break()
    //     }
    //   }
    // })
    // fsDataOutputStream.close()

    // fileSystem.copyFromLocalFile(
    //   false,
    //   true,
    //   new Path("C:\\Users\\alan\\Downloads\\QuarkCloudDrive-v2.5.43-release-pckk@other_ch-20230728205059.exe"),
    //   new Path("/test1/test2")
    // )
    // fileSystem.copyToLocalFile(
    //   true,
    //   new Path("/test1/test2/QuarkCloudDrive-v2.5.43-release-pckk@other_ch-20230728205059 (1).exe"),
    //   new Path("D:\\dev\\idea\\project\\demo\\hdfs\\src\\main\\resources\\a.exe"),
    //   true
    // )
    // fileSystem.delete(new Path("/test1/test2/"), true)
    // fileSystem.rename(
    //   new Path("/hdfs-jar-with-dependencies1.jar"),
    //   new Path("/test1/hdfs-jar-with-dependencies.jar")
    // ) RemoteIterator[LocatedFileStatus] = fileSystem.listFiles(new Path("/"), true)
    //     while (remoteIterator.hasNext) {
    //       val locatedFileStatus: LocatedFileStatus = remoteIterator.next()
    //       println(locatedFileStatus.getPath.getName)
    //       println(locatedFileStatus.getPath)
    //       if (locatedFileStatus.isFile != true) {println("alan")}
    //       println(locatedFileStatus.isDirectory)
    //       println(locatedFileStatus.getPermission)
    //       println(locatedFileStatus.getOwner)
    //       println(locatedFileStatus.getGroup)
    //       println(locatedFileStatus.getLen)
    //       println(locatedFileStatus.getBlockSize)
    //       println(locatedFileStatus.getModificationTime)
    //       println(locatedFileStatus.getReplication)
    //       println(locatedFileStatus.getBlockLocations.toBuffer)
    //       println(locatedFileStatus.getAccessTime)
    //       println(locatedFileStatus.hasAcl)

    // val remoteIterator: RemoteIterator[LocatedFileStatus] = fileSystem.listFiles(new Path("/"), true)
    // while (remoteIterator.hasNext) {
    //   val fileStatus: LocatedFileStatus = remoteIterator.next()
    //   println(fileStatus.getPath)
    //   println(fileStatus.isFile)
    // }

    // val statuses: Array[FileStatus] = fileSystem.listStatus(new Path("/")).map((fileStatus: FileStatus) => {
    //   println(fileStatus.getPath)
    //   println(fileStatus.isFile)
    //   fileStatus
    // })

    // println(fileSystem.getClass)
    // println(classOf[FileSystem])

  }
}

