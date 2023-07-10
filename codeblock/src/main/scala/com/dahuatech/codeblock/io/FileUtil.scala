package com.dahuatech.codeblock.io

import org.apache.commons.io.FileUtils

import java.io._
import java.nio.charset.StandardCharsets
import java.util.Properties

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.template.io</p>
 * <p>className: FileUtil</p>
 * <p>date: 2023/5/4</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
/*
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>${commons.io.version}</version>
        </dependency>
 */
object FileUtil {

  def main(args: Array[String]): Unit = {



  }

  /**
   * 加载jar包外的配置文件作为Properties对象 只能加载jar包外的配置文件
   *
   * @param filename
   * @return
   */
  def loadFileAsProperties(filename: String): Properties = {
    val fis = new FileInputStream(new File(filename))
    val prop = new Properties()
    prop.load(fis)

    prop
  }

  /**
   * 加载jar包外的配置文件作为UTF-8字符串 只能加载jar包外的配置文件
   *
   * @param filename
   * @return
   */
  def loadFileAsString(filename: String): String = FileUtils.readFileToString(new File(filename), StandardCharsets.UTF_8)

  /**
   * 加载资源作为Properties对象 只能加载jar包内的资源配置文件 或者计算平台上资源配置文件
   *
   * @param filename
   * @return
   */
  def loadResourceAsProperties(filename: String): Properties = {
    val is: InputStream = ClassLoader.getSystemClassLoader.getResourceAsStream(filename)
    val prop = new Properties()
    prop.load(is)

    prop
  }

  /**
   * 加载资源作为UTF-8字符串 只能加载jar包内的资源配置文件 或者计算平台上资源配置文件
   *
   * @param filename
   * @return
   */
  def loadResourceAsString(filename: String): String = {
    val is: InputStream = ClassLoader.getSystemClassLoader.getResourceAsStream(filename)
    val fileContent: String = readInputStreamToString(is)

    fileContent
  }

  /**
   * 从流中读取数据并转化为UTF-8字符串
   *
   * @param is
   * @return
   */
  def readInputStreamToString(is: InputStream, bufSize: Int = 1024): String = {
    var byteArrayOutputStream: ByteArrayOutputStream = null
    var bis: BufferedInputStream = null

    try {
      byteArrayOutputStream = new ByteArrayOutputStream(bufSize * 4)
      bis = new BufferedInputStream(is)

      var len = 0
      val buf = new Array[Byte](bufSize)
      len = bis.read(buf)
      while (len != -1) {
        byteArrayOutputStream.write(buf, 0, len)
        len = bis.read(buf)
      }

    } finally {
      if (is != null) is.close()
      if (bis != null) bis.close()
      if (byteArrayOutputStream != null) byteArrayOutputStream.close()
    }

    new String(byteArrayOutputStream.toByteArray, StandardCharsets.UTF_8)
  }
}
