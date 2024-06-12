package org.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class ScanFilter {

    public static void main(String[] args) throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        //tạo connection
        Connection connection = ConnectionFactory.createConnection(configuration);

        Table table = connection.getTable(TableName.valueOf("myTable"));
        //Xác định keyRow
        Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,
                new BinaryComparator(Bytes.toBytes("2")));

        //scan class
        Scan userScan = new Scan();
        userScan.setFilter(filter);
        //getting scan result
        ResultScanner userScanResult = table.getScanner(userScan);
        
        for (Result res: userScanResult){
            System.out.println(res);
        }

        userScanResult.close();
    }
}
