package com.dahuatech.parquet.demo

import com.dahuatech.parquet.bean.Person
import org.apache.avro.reflect.ReflectData
import org.apache.avro.{Schema, SchemaBuilder}
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.Path
import org.apache.hadoop.security.UserGroupInformation
import org.apache.parquet.avro.AvroParquetWriter
import org.apache.parquet.hadoop.metadata.CompressionCodecName
import org.apache.parquet.hadoop.{ParquetFileWriter, ParquetWriter}

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


object Demo {
  UserGroupInformation.setLoginUser(UserGroupInformation.createRemoteUser("root"))
  val configuration: Configuration = new Configuration()
  configuration.addResource("core-site.xml")

  val schema: Schema = SchemaBuilder.builder()
    .record("Person")
    .fields()
    .requiredString("name")
    .requiredInt("age")
    .endRecord()

  val parquetWriter: ParquetWriter[Person] = AvroParquetWriter
    .builder[Person](new Path("/user/hive/warehouse/stu/dt=2025/demo.parquet")).
    withConf(configuration)
    .withDataModel(ReflectData.get())
    .withCompressionCodec(CompressionCodecName.UNCOMPRESSED)
    .withSchema(schema)
    .withWriteMode(ParquetFileWriter.Mode.OVERWRITE)
    .build()

  def generateRandomString(length: Int): String = {
    val chars: immutable.IndexedSeq[Char] = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9') ++ Seq('/', '+')
    val random = new Random
    (1 to length).map(_ => chars(random.nextInt(chars.length))).mkString
  }

  def main(args: Array[String]): Unit = {
    for (i <- 0 until 100) {
      parquetWriter.write(Person(generateRandomString(100), i))
    }
    parquetWriter.close()
  }
}
