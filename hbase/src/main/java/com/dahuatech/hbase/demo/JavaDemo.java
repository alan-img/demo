package com.dahuatech.hbase.demo;

import com.dahuatech.hbase.utils.HbaseUtil;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.hbase.demo</p>
 * <p>className: JavaDemo</p>
 * <p>date: 2023/4/13</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
public class JavaDemo {
    public static void main(String[] args) throws IOException {

        Table table = HbaseUtil.getHbaseConnection().getTable(TableName.valueOf("p_face_cluster"));

        ArrayList<Put> list = new ArrayList<>();
        for (int i = 3000000; i < 4000000; i++) {
            String rowKey = "999999921143228" + i;
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("feature"), Bytes.toBytes("C4QrvUBPjT3ol327n3TuvAKu0jwmg129w5HHPN2nHD2vy429Fa8WPYkEkL3O7d27oe1nvdG7abxR/RW8SAPWPdfnnbv7vKi7++CPPUhLpL1hxJi6P1HkvIcdCj0jIYc9C6iSvbrf1Tw1Jvk8zQQBvTPEoj2/Jtu9tpjQPBpLrrwtvNW9dIpSPcqm2Lyf4KO9rJFMPXegrL2zysQ9cK8CvbSkBr0SvaM9HHxZvPjuHDwaJ8e8pTRtPRK9o7zNco27FYuvvFx9E72TC5S6s+4rPjJYbb2ONHK9D4GLvBa8Wr0xS6k9NBk1PelLAb2/SsK8M6C7PIY2hD3lyfE9tpjQvOkQ9z1wi5s9IqgNPXc0dz2NA8e8K2fDvYrtbDyYp6s90GQAPeRyiD0Pg2I9NBm1vHbfZDxCiyW9UgravVUgNLxnkds8WO6/OmxElr3o38u9dkuaPVIK2jmrhAg9jScuvcNJ+bwEbxq9T/R/vZd2ALzB0P+8cGc0PBx82Tw2tpW9MW+QvaRLkLwpvZ69uyekPV8nOL3ZYJc90K6lPcUuqL0cxKc9D6fJPdvmVD2bUVC9+R/IuuTgFL2BX2K9vMSEvPpnFrsNUre9a8ucu229D70Pp8m8uFkYPm2ZKL0UH3q8564gOdlgFzxtdUG9KjYYvXbf5D37cgO9ly6yvfvgjz38yew85RFAvTEnQj0M2b09in0JvSrKYr3WJta8H9oBPeL7ZbwXEW296qATvbDY0T1zEVm9KFHpO6z9AT1//Qu+ugO9PHMPgj0KGHY9FUGKPLtLi7ylfDs8C4QrvSdEpb3zO2K8PmgHPS59nb0yfNQ844sCPo5Wgr3yCrc9NUrgPVg2Dr03C6i9d1YHPI3dCL7etOC84JmPPIeLlr12JzM7QkPXvFlnuTy/bqk7KhIxPfiC573eIBY+xS6ovTVK4L1cfZO8e8PKvGe1Qr3RJx89h4uWvQkvmT36+Qk9ri4tPdrZkLo+IDm8kwsUvY0DRz1pUqM728JtPBkaA77WJta9kBkhO/xZibw7CIg8okJ6vFZR3zxVIDS9QRKsvfRfyT0q7sk8xoO6PTHf8zyiQno9S9FhPC4RaDvNmEs9qJIVPeiX/Tz7coM94cq6Owy11j1izwW+XH2TPXBDzT0mg12+z1mTvSm9nj28fDY9RAQfPhoDYD03L4+8cK+Cve/03D3RAzg9IRhxPUIf8D0cfFk9+KZOPFNf7DwIkri9L2b6vXBntD3+Zk29R9KqPNBmV72HHQo8LhFove8YxL24WRi9oASLPcB77bqWwny9NK1/PKhuLr1p5u28epIfveGmUz06tUy9qErHvOZZDj2w2NG9QL2ZPahKRz3ywmg8HT0hPQ=="));
            put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("v1"), Bytes.toBytes("{\"faceBottom\":876,\"faceRight\":516,\"faceLeft\":435,\"faceImgUrl\":\"http://10.38.24.2/images/myFaceSmall/510799020013100001630120210511180753170880667088.jpg\",\"faceTop\":804,\"capTime\":1681223860993,\"channelId\":\"OV9PFi9sA1CUAB4NN3SUIH\",\"mask\":0}"));
            list.add(put);

            if (list.size() >= 200) {
                table.put(list);
                list.clear();
            }
        }
    }
}