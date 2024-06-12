package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.IOExceptionSupplier;

import java.io.IOException;

public class checkRowExists {
    public static void main(String[] args) throws IOException {
        // Tạo cấu hình HBase
        Configuration configuration = HBaseConfiguration.create();
        Connection connection = null;
        Table table = null;

        try {
            // Tạo kết nối HBase
            connection = ConnectionFactory.createConnection(configuration);

            // Lấy bảng từ kết nối
            table = connection.getTable(TableName.valueOf("myTable"));

            // Tạo đối tượng Get cho row 3
            Get get = new Get(Bytes.toBytes("3"));

            // Kiểm tra sự tồn tại của row 1
            boolean exists = table.exists(get);
            System.out.println("Row '3' exists: " + exists);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Đóng bảng và kết nối để giải phóng tài nguyên
            try {
                if (table != null) table.close();
                if (connection != null) connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
