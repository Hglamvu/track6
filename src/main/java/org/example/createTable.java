package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

public class createTable {
    public static void main(String[] args) {
        Configuration conf = HBaseConfiguration.create();

        try (Connection connection = ConnectionFactory.createConnection(conf);
             //tạo kết nối
             Admin admin = connection.getAdmin()) {
            //tên bảng
            HTableDescriptor tableName = new HTableDescriptor(TableName.valueOf("myTable"));
            //thêm family Column
            tableName.addFamily(new HColumnDescriptor("colfam1"));
            tableName.addFamily(new HColumnDescriptor("colfam2"));
            //nếu tồn tại thì true
            if (!admin.tableExists(tableName.getTableName())) {
                admin.createTable(tableName);
                System.out.println("Table created successfully.");
            } else {
                System.out.println("Table already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
