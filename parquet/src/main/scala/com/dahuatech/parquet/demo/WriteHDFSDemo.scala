package com.dahuatech.parquet.demo

import com.dahuatech.parquet.bean.Person
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.{Input, Output}
import com.sun.jersey.core.header.FormDataContentDisposition.name
import org.apache.avro.generic.{GenericData, GenericRecord}
import org.apache.avro.reflect.ReflectData
import org.apache.avro.{Schema, SchemaBuilder}
import org.apache.commons.io.output.ByteArrayOutputStream
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Options.HandleOpt.path
import org.apache.hadoop.fs.Path
import org.apache.hadoop.security.UserGroupInformation
import org.apache.parquet.avro.{AvroParquetReader, AvroParquetWriter, AvroReadSupport}
import org.apache.parquet.hadoop.example.GroupReadSupport
import org.apache.parquet.hadoop.metadata.CompressionCodecName
import org.apache.parquet.hadoop.{ParquetFileWriter, ParquetReader, ParquetWriter}

import java.util
import java.util.Random
import scala.collection.immutable
import scala.collection.mutable.ListBuffer


/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.parquet.demo</p>
 * <p>className: Demo</p>
 * <p>date: 1/2/2024</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */

object WriteHDFSDemo {
  UserGroupInformation.setLoginUser(UserGroupInformation.createRemoteUser("root"))
  val configuration: Configuration = new Configuration()
  configuration.addResource("core-site.xml.bak")

  def getSchema(): Schema = SchemaBuilder
      .builder()
      .record("com.dahuatech.parquet.bean.Person")
      .fields()
      .requiredString("name")
      .requiredLong("age")
      .endRecord()

  def getParquetWriter[T](path: String): ParquetWriter[T] = {
    val parquetWriter: ParquetWriter[T] = AvroParquetWriter
      .builder[T](new Path(path))
      .withConf(configuration)
      .withDataModel(ReflectData.get())
      .withCompressionCodec(CompressionCodecName.UNCOMPRESSED)
      .withSchema(getSchema())
      .withWriteMode(ParquetFileWriter.Mode.OVERWRITE)
      .build()
    parquetWriter
  }

  def getParquetReader[T](path: String): ParquetReader[T] = {
    val parquetReader: ParquetReader[T] = ParquetReader
      .builder[T](new AvroReadSupport[T](), new Path(path))
      .withConf(configuration)
      .build()
    parquetReader
  }

  def generateRandomString(length: Int): String = {
    val chars: immutable.IndexedSeq[Char] = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9') ++ Seq('/', '+')
    val random = new Random
    (1 to length).map(_ => chars(random.nextInt(chars.length))).mkString
  }

  /**
   * 测试序列化和反序列化
   */
  def useKryoSerDer(): Unit = {
    val kryo: Kryo = new Kryo()
    kryo.register(classOf[String], 1)
    kryo.register(classOf[Int], 1)
    val p: Person = Person("alan", 23)
    val byteArrayOutputStream: ByteArrayOutputStream = new ByteArrayOutputStream()
    val output: Output = new Output(byteArrayOutputStream)
    kryo.writeObject(output, p)
    output.flush()
    println(new String(byteArrayOutputStream.toByteArray))
    val p1: Person = kryo.readObject(new Input(byteArrayOutputStream.toByteArray), classOf[Person])
    println(p1)
  }

  def main(args: Array[String]): Unit = {
    for (i <- 0 until 3) {
      val parquetWriter: ParquetWriter[Person] = getParquetWriter[Person](s"/user/hive/warehouse/stu/dt=${2024 + i}/${i}.parquet")
      for (j <- 0 until 2200000) {
        parquetWriter.write(Person(generateRandomString(50), i))
      }
      parquetWriter.close()
    }

    // val record: Person = parquetReader.read()
    // if (record != null) {
    //   println(record)
    // }
  }
}
