package com.dahuatech.jedis.utils

import org.slf4j.{Logger, LoggerFactory}

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
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  /**
   * 加载jar包外的资源文件作为Properties对象
   *
   * @param filename
   * @return
   */
  def loadFileAsProperties(filename: String): Properties = {
    var bufferedInputStream: BufferedInputStream = null
    val properties: Properties = new Properties()
    try {
      bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(filename)))
      properties.load(bufferedInputStream)
    } catch {
      case exp: Exception => logger.warn("load file as string failed", exp)
    } finally {
      if (bufferedInputStream != null) close(bufferedInputStream)
    }

    properties
  }

  /**
   * 加载jar包外的资源文件作为UTF-8字符串
   *
   * @param filename
   * @return
   */
  def loadFileAsString(filename: String): String = {
    var bufferedInputStream: BufferedInputStream = null
    try {
      bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(filename)))
    } catch {
      case exp: Exception => logger.warn("load file as string failed", exp)
    } finally {
      if (bufferedInputStream != null) close(bufferedInputStream)
    }

    readInputStreamToString(bufferedInputStream)
  }

  /**
   * 加载Jar包内resource目录下的资源文件作为Properties对象
   *
   * @param filename
   * @return
   */
  def loadResourceAsProperties(filename: String): Properties = {
    var inputStream: InputStream = null
    var properties: Properties = new Properties()
    try {
      inputStream = ClassLoader.getSystemClassLoader.getResourceAsStream(filename)
      properties.load(inputStream)
    } catch {
      case exp: Exception => logger.warn("load resource as properties failed", exp)
    } finally {
      if (inputStream != null) close(inputStream)
    }

    properties
  }

  /**
   * 加载Jar包内resource目录下的资源文件作为UTF-8字符串
   *
   * @param filename
   * @return
   */
  def loadResourceAsString(filename: String): String = {
    var inputStream: InputStream = null
    try {
      inputStream = ClassLoader.getSystemClassLoader.getResourceAsStream(filename)
    } catch {
      case exp: Exception => logger.warn("load resource as string failed", exp)
    } finally {
      if (inputStream != null) close(inputStream)
    }

    readInputStreamToString(inputStream)
  }

  /**
   * 从流中读取数据并转化为UTF-8字符串
   *
   * @param is
   * @return
   */
  def readInputStreamToString(inputStream: InputStream, bufSize: Int = 1024): String = {
    var byteArrayOutputStream: ByteArrayOutputStream = null
    var bufferedInputStream: BufferedInputStream = null

    try {
      byteArrayOutputStream = new ByteArrayOutputStream(bufSize * 4)
      bufferedInputStream = new BufferedInputStream(inputStream)

      var len = 0
      val buf = new Array[Byte](bufSize)
      len = bufferedInputStream.read(buf)
      while (len != -1) {
        byteArrayOutputStream.write(buf, 0, len)
        len = bufferedInputStream.read(buf)
      }
    } catch {
      case exp: Exception => logger.warn("from input stream read string failed", exp)
    } finally {
      if (bufferedInputStream != null) close(bufferedInputStream)
      if (byteArrayOutputStream != null) close(byteArrayOutputStream)
    }

    new String(byteArrayOutputStream.toByteArray, StandardCharsets.UTF_8)
  }

  /**
   * 关闭输入流
   *
   * @param inputStream
   */
  def close(inputStream: InputStream): Unit = {
    try {
      inputStream.close()
    } catch {
      case exp: Exception => logger.warn("close inputStream resource failed", exp)
    }
  }

  /**
   * 关闭输出流
   *
   * @param outputStream
   */
  def close(outputStream: OutputStream): Unit = {
    try {
      outputStream.close()
    } catch {
      case exp: Exception => logger.warn("close outputStream resource failed", exp)
    }
  }
}
