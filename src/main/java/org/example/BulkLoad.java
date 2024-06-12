    package org.example;

    import org.apache.hadoop.conf.Configuration;
    import org.apache.hadoop.hbase.HBaseConfiguration;
    import org.apache.hadoop.hbase.mapreduce.ImportTsv;
    import org.apache.hadoop.util.ToolRunner;

    import java.io.IOException;

    public class BulkLoad {
        public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
            Configuration conf = HBaseConfiguration.create();

            String[] importArgs = new String[]{
                    "-Dimporttsv.separator=,", // Đặt phân tách cột là dấu ","
                        "-Dimporttsv.columns=HBASE_ROW_KEY,personal:name,contact:email,contact:phone", // Thiết lập lên các tên cột
                    "user_table", // Tên của bảng HBase
                    "/Users/lamvuhoang/Documents/info.csv" // đường dẫn tập csv
            };

            try {
                int status = ToolRunner.run(conf, new ImportTsv(), importArgs);
                //dùng công cụ ImportTsv để tạo HFile
                if (status == 0) {
                    System.out.println("Bulk load thành công!");
                } else {
                    System.err.println("Bulk load thất bại!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
