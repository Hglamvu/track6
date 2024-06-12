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

public class createTableWithTTL {

    public static void main(String[] args) {
        // Tạo cấu hình HBase
        Configuration conf = HBaseConfiguration.create();
        Connection con = null;
        Admin admin = null;

        try {
            // Tạo kết nối HBase
            con = ConnectionFactory.createConnection(conf);
            admin = con.getAdmin();

            // Tạo mô tả bảng
            HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("Table_test"));

            // Tạo mô tả cột fam với TTL
            HColumnDescriptor columnDescriptor = new HColumnDescriptor("colfam1");
            columnDescriptor.setTimeToLive(604800);

            // Thêm cột vào bảng
            tableDescriptor.addFamily(columnDescriptor);

            // Tạo bảng với mô tả
            admin.createTable(tableDescriptor);

            System.out.println("Table created successfully with TTL");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Đóng admin và kết nối để giải phóng tài nguyên
            try {
                if (admin != null) admin.close();
                if (con != null) con.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
