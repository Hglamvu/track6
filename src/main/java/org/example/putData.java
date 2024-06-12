package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class putData {
    public static void main(String [] args) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);
        //tạo connect
        Table table = connection.getTable(TableName.valueOf("myTable"));
        //xác định keyRow
        Put put = new Put(Bytes.toBytes("2"));
        //thêm dữ liệu vào từng family column
        put.addColumn(Bytes.toBytes("colfam1"),Bytes.toBytes("first_name"), Bytes.toBytes("Hoang"));
        put.addColumn(Bytes.toBytes("colfam1"),Bytes.toBytes("last_name"), Bytes.toBytes("Lam Vu"));
        put.addColumn(Bytes.toBytes("colfam2"),Bytes.toBytes("address"), Bytes.toBytes("Hanoi"));

        table.put(put);

    }
}
