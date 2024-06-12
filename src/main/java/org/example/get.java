package org.example;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class get{
    public static void main (String [] args) throws IOException {
        // Tạo cấu hình HBase
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = ConnectionFactory.createConnection(configuration);

        // Lấy bảng từ kết nối
        Table table = connection.getTable(TableName.valueOf("myTable"));

        // Tạo đối tượng Get
        Get g = new Get(Bytes.toBytes("2"));

        // Đọc dữ liệu
        Result rs = table.get(g);

        // Kiểm tra nếu kết quả không rỗng
        if (!rs.isEmpty()) {
            // Đọc giá trị từ đối tượng kết quả
            byte[] value1 = rs.getValue(Bytes.toBytes("colfam1"), Bytes.toBytes("first_name"));
            byte[] value2 = rs.getValue(Bytes.toBytes("colfam1"), Bytes.toBytes("last_name"));

            // Chuyển đổi byte[] thành String
            String name1 = value1 != null ? Bytes.toString(value1) : "null";
            String name2 = value2 != null ? Bytes.toString(value2) : "null";

            // In ra giá trị
            System.out.println("first_name: " + name1 + ", last_name: " + name2);
        } else {
            System.out.println("Row not found.");
        }

        // Đóng bảng và kết nối
        table.close();
        connection.close();
    }
}