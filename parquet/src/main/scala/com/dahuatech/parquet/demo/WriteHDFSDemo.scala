package com.dahuatech.parquet.demo

import com.dahuatech.parquet.bean.Person
import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.{Input, Output}
import org.apache.avro.generic.GenericRecord
import org.apache.avro.reflect.ReflectData
import org.apache.avro.{Schema, SchemaBuilder}
import org.apache.commons.io.output.ByteArrayOutputStream
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.security.UserGroupInformation
import org.apache.parquet.avro.{AvroParquetWriter, AvroReadSupport}
import org.apache.parquet.hadoop.metadata.CompressionCodecName
import org.apache.parquet.hadoop.{ParquetFileWriter, ParquetReader, ParquetWriter}

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

object WriteHDFSDemo {
  UserGroupInformation.setLoginUser(UserGroupInformation.createRemoteUser("root"))
  val configuration: Configuration = new Configuration()
  configuration.addResource("core-site.xml")

  def getPersonSchema(): Schema = SchemaBuilder
    .builder()
    .record(classOf[Person].getSimpleName)
    .fields()
    .requiredString("name")
    .requiredLong("age")
    .endRecord()

  def getParquetWriter[T](fileAbsolutePath: String, schema: Schema): ParquetWriter[T] = {
    val parquetWriter: ParquetWriter[T] = AvroParquetWriter
      .builder[T](new Path(fileAbsolutePath))
      .withConf(configuration)
      .withDataModel(ReflectData.get())
      .withCompressionCodec(CompressionCodecName.UNCOMPRESSED)
      .withSchema(schema)
      .withWriteMode(ParquetFileWriter.Mode.OVERWRITE)
      .build()
    parquetWriter
  }

  def getParquetReader(fileAbsolutePath: String): ParquetReader[GenericRecord] = {
    val parquetReader: ParquetReader[GenericRecord] = ParquetReader
      .builder[GenericRecord](new AvroReadSupport[GenericRecord](), new Path(fileAbsolutePath))
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
  def useKryoSerObject[T](obj: T): Array[Byte] = {
    val kryo: Kryo = new Kryo()

    val byteArrayOutputStream: ByteArrayOutputStream = new ByteArrayOutputStream()
    val output: Output = new Output(byteArrayOutputStream)
    kryo.writeObject(output, obj)
    output.flush()
    output.close()

    byteArrayOutputStream.toByteArray
  }

  def useKryoDerObject[T](objByteArray: Array[Byte], clazz: Class[T]): T  = {
    val kryo: Kryo = new Kryo()

    kryo.readObject(new Input(objByteArray), clazz)
  }

  def main(args: Array[String]): Unit = {
    for (i <- 0 until 3) {
      val parquetWriter: ParquetWriter[Person] = getParquetWriter[Person](s"/user/hive/warehouse/stu/dt=${2024 + i}/${i}.parquet", getPersonSchema())
      for (j <- 0 until 1000) {
        val randomString: String = generateRandomString(50)
        parquetWriter.write(Person(randomString, j))
      }
      parquetWriter.close()
    }

    val fileAbsolutePath: String = s"/user/hive/warehouse/stu/dt=2024/0.parquet"
    val parquetReader: ParquetReader[GenericRecord] = getParquetReader(fileAbsolutePath)
    val genericRecord: GenericRecord = parquetReader.read()
    println(genericRecord)
    parquetReader.close()
  }
}
