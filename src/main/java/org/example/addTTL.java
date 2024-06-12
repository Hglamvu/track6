package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import java.io.IOException;

public class addTTL {
    public static void main(String[] args) {
        Configuration conf = HBaseConfiguration.create();
        Connection con = null;
        Admin admin = null;


        try {
            con = ConnectionFactory.createConnection(conf);
            //tạo kết nối
            admin = con.getAdmin();

            // Lấy mô tả cột fam
            HColumnDescriptor columnDescriptor = new HColumnDescriptor("colfam1");
            columnDescriptor.setTimeToLive(604800);

            // Thêm cột fam vào bảng
            admin.modifyColumn(TableName.valueOf("table"), columnDescriptor);

            System.out.println("TTL added to column family successfully");

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
