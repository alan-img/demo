package com.dahuatech.parquet.demo

import com.dahuatech.parquet.bean.Person
import com.dahuatech.parquet.demo.WriteHDFSDemo.{configuration, getPersonSchema}
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.{Input, Output}
import org.apache.avro.generic.GenericRecord
import org.apache.avro.reflect.ReflectData
import org.apache.avro.{Schema, SchemaBuilder}
import org.apache.commons.io.output.ByteArrayOutputStream
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.security.UserGroupInformation
import org.apache.parquet.avro.{AvroParquetReader, AvroParquetWriter, AvroReadSupport}
import org.apache.parquet.hadoop.metadata.CompressionCodecName
import org.apache.parquet.hadoop.{ParquetFileWriter, ParquetReader, ParquetWriter}

import java.io.{File, FileInputStream}
import java.util.Random
import scala.collection.immutable


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

object WriteLocalDemo {
  def getPersonSchema(): Schema = SchemaBuilder
      .builder()
      .record(classOf[Person].getSimpleName)
      .fields()
      .requiredString("name")
      .requiredLong("age")
      .endRecord()

  def createParquetWriter[T](fileAbsolutePath: String, schema: Schema): ParquetWriter[T] = {
    val parquetWriter: ParquetWriter[T] = AvroParquetWriter
      .builder[T](new Path(fileAbsolutePath))
      .withDataModel(ReflectData.get())
      .withSchema(schema)
      .build()
    parquetWriter
  }

  def createParquetReader(fileAbsolutePath: String): ParquetReader[GenericRecord] = {
    val parquetReader: ParquetReader[GenericRecord] = ParquetReader
      .builder[GenericRecord](new AvroReadSupport[GenericRecord](), new Path(fileAbsolutePath))
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
    val fileAbsolutePath: String = "D:\\dev\\idea\\project\\demo\\parquet\\target\\test.parquet"
    new File(fileAbsolutePath).delete()

    val parquetWriter: ParquetWriter[Person] = createParquetWriter[Person](
      fileAbsolutePath,
      getPersonSchema()
    )
    for (i <- 0 until 10) {
      parquetWriter.write(Person(generateRandomString(50), i))
    }
    parquetWriter.close()

    val parquetReader: ParquetReader[GenericRecord] = createParquetReader(fileAbsolutePath)
    val genericRecord: GenericRecord = parquetReader.read()
    println(genericRecord)
    parquetReader.close()
  }
}
