package com.dahuatech.hdfs.utils

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.tools.tar.{TarEntry, TarInputStream}

import java.io.{BufferedReader, InputStreamReader}
import java.net.URI
import java.util.zip.GZIPInputStream

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.hdfs.utils</p>
 * <p>className: ReadFileFromTarballUtil</p>
 * <p>date: 2023/5/5</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

object ReadFileFromTarballUtil {
  /**
   * 从tarball读取文件内容
   */
  def readFileFromTarball(): Unit = {
    val fs: FileSystem = FileSystem.get(URI.create("hdfs:///"), new Configuration(), "root")
    val tarIn = new TarInputStream(new GZIPInputStream(fs.open(new Path("hdfs://hadoop101:8020/demo.tar.gz"))))
    var entry: TarEntry = tarIn.getNextEntry
    while (entry != null) {
      if (entry.isFile && entry.getName == "face_region_info.csv") {
        val bufferedReader = new BufferedReader(new InputStreamReader(tarIn))
        var line = bufferedReader.readLine()
        while (line != null) {
          line = bufferedReader.readLine()
          println(line)
        }
      }
      entry = tarIn.getNextEntry
    }
  }
}
