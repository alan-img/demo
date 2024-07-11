import org.apache.hadoop.hbase.client.Connection
import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.client.Table;

conf = @shell.hbase.configuration
conn = ConnectionFactory.createConnection(conf)

tableName = TableName::valueOf("fc:p_face_dossier")
table = conn.getTable(tableName)

rowKey = Bytes::toBytes("00058188412819386752474534")
pt = org.apache.hadoop.hbase.client.Put.new(rowKey)

pt.addColumn(Bytes::toBytes("info"), Bytes::toBytes("serviceNode"), Bytes::toBytes(java.lang.Integer::valueOf(121).to_java:int))

pt.addColumn(Bytes::toBytes("info"), Bytes::toBytes("ageGroup"), Bytes::toBytes(java.lang.Integer::valueOf(2).to_java:int))

pt.addColumn(Bytes::toBytes("info"), Bytes::toBytes("gender"), Bytes::toBytes(java.lang.Integer::valueOf(0).to_java:int))

pt.addColumn(Bytes::toBytes("info"), Bytes::toBytes("dossierId"), Bytes::toBytes("13162422224585296492783534"))

pt.addColumn(Bytes::toBytes("info"), Bytes::toBytes("identificationType"), Bytes::toBytes("111"))

pt.addColumn(Bytes::toBytes("info"), Bytes::toBytes("identificationId"), Bytes::toBytes("330281198605260414"))

pt.addColumn(Bytes::toBytes("info"), Bytes::toBytes("name"), Bytes::toBytes("严杰"))

pt.addColumn(Bytes::toBytes("info"), Bytes::toBytes("repositoryId"), Bytes::toBytes("20b507ea64cf4f6ebc7a14e635af0f83"))

pt.addColumn(Bytes::toBytes("info"), Bytes::toBytes("memberId"), Bytes::toBytes("ac5041c5f62b447d86afc64501fa9e02"))

pt.addColumn(Bytes::toBytes("info"), Bytes::toBytes("identifyTime"), Bytes::toBytes(java.lang.Long::valueOf(1689249854745).to_java:long))

pt.addColumn(Bytes::toBytes("info"), Bytes::toBytes("faceIdentifyImgUrl"), Bytes::toBytes("http://41.205.74.213:38498/image/smart_A64o7FP8_001/eagle-pic/download/pic/tYTy4XG3/home/hadoop/picture/static/24090/1686744744061/UserBig_1686774569151_318805.JPG"))

table.put(pt)

conn.close