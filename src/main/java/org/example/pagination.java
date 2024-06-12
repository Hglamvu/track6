package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class pagination {

    public static void main(String[] args) {
        Configuration config = HBaseConfiguration.create();
        //tạo kết nối
        try (Connection connection = ConnectionFactory.createConnection(config)) {
            Table table = connection.getTable(TableName.valueOf("user_table"));
            //lấy bảng user_table từ HBase

            int pageSize = 2; // Số hàng mỗi trang
            byte[] lastRow = null;
            //dòng cuối cùng của trang hiện tại, dùng để xác định startRow cho trang tiếp theo

            for (int page = 0; page < 3; page++) { // Ví dụ lấy 3 trang dữ liệu
                Scan scan = new Scan();
                //tạo một đối tượng Scan
                scan.setFilter(new PageFilter(pageSize));
                //đặt bộ lọc PageFilter để giới hạn số hàng mỗi trang.

                if (lastRow != null) {
                    byte[] startRow = Bytes.add(lastRow, new byte[1]);
                    scan.withStartRow(startRow);
                    //startRow được thiết lập bằng cách thêm một byte vào lastRow để tránh lặp lại hàng cuối của trang trước.
                }

                try (ResultScanner scanner = table.getScanner(scan)) {
                    int count = 0;
                    for (Result result : scanner) {
                        System.out.println("Row: " + Bytes.toString(result.getRow()));
                        System.out.println("Name: " + Bytes.toString(result.getValue(Bytes.toBytes("personal"), Bytes.toBytes("name"))));
                        System.out.println("Email: " + Bytes.toString(result.getValue(Bytes.toBytes("contact"), Bytes.toBytes("email"))));
                        System.out.println("Phone: " + Bytes.toString(result.getValue(Bytes.toBytes("contact"), Bytes.toBytes("phone"))));
                        System.out.println("---------");

                        lastRow = result.getRow();
                        count++;
                    }

                    // Nếu số hàng ít hơn kích thước trang, kết thúc
                    if (count < pageSize) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
