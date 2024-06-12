package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class deleteData {
    public static void main(String [] args) throws IOException{
        Configuration configuration = HBaseConfiguration.create();
        //tạo kết nối
        Connection connection = ConnectionFactory.createConnection(configuration);

        Table table = connection.getTable(TableName.valueOf("myTable"));
        //xác định keyRow
        Delete delete = new Delete(Bytes.toBytes("1"));
        //xác định dòng xoá
        delete.addColumn(Bytes.toBytes("colfam1"),Bytes.toBytes("first_name"));
        table.delete(delete);
        table.close();
    }
}
