# README - Part 8

## Mục Tiêu
Phần này của dự án sử dụng thuật toán FPGrowth trong thư viện Spark MLlib để khám phá các mối quan hệ liên quan đến hành vi mua sắm từ dữ liệu bán lẻ e-commerce.

## Các Bước Thực Hiện

1. **Load Dữ Liệu:** Đọc dữ liệu từ tập tin CSV chứa thông tin về các giao dịch mua sắm.

2. **Xử Lý Dữ Liệu:** Loại bỏ dữ liệu trùng lặp và các giao dịch không hợp lệ.

3. **Xây Dựng Mô Hình FPGrowth:** Sử dụng thuật toán FPGrowth để tìm kiếm các tập mặt hàng phổ biến và xây dựng các quy tắc liên kết.

4. **Hiển Thị Kết Quả:** In ra màn hình các tập mặt hàng phổ biến và quy tắc liên kết với các thông số như tần suất, confidence, lift.

## Cấu Trúc Thư Mục

- `src/main/java/com/spark/part8/Main.java`: Mã nguồn chính cho phần thực hiện xử lý và mô hình FPGrowth.
- `data/retails.csv`: Tập dữ liệu đầu vào chứa thông tin về giao dịch mua sắm.
- `output/frequent_itemsets/`: Thư mục chứa kết quả về các tập mặt hàng phổ biến.
- `output/association_rules/`: Thư mục chứa kết quả về các quy tắc liên kết.

## Yêu Cầu

- Apache Spark 3.5.0
- Java 8

## Cách Sử Dụng

1. Chạy ứng dụng bằng cách sử dụng Apache Maven:

    ```bash
    mvn clean package
    spark-submit --class com.spark.part8.Main --master local[*] target/part8-1.0-SNAPSHOT.jar
    ```

2. Xem kết quả được hiển thị trên màn hình và kiểm tra các tập tin trong thư mục `output`.

3. Tùy chỉnh cấu hình và thay đổi tham số mô hình trong mã nguồn nếu cần thiết.

## Kết Luận


Kết quả mô hình FPGrowth trong phần này bao gồm hai bảng chính: frequentItemsets và associationRules. Dưới đây là giải thích chi tiết về kết quả:

Bảng frequentItemsets:
Bảng này hiển thị các tập phổ biến của các mặt hàng và tần suất xuất hiện của chúng trong tập dữ liệu.

Ví dụ:

[21889] xuất hiện 590 lần.
[21981] xuất hiện 272 lần.
Bảng associationRules:
Bảng này hiển thị các quy tắc liên kết giữa các tập mặt hàng và chúng được sắp xếp theo mức độ confidence (độ chắc chắn).

Ví dụ:

Nếu [22384, 22382] xuất hiện, thì có xác suất 61.72% một giao dịch chứa [20728].
Nếu [21094] xuất hiện, thì có xác suất 70.21% một giao dịch chứa [21080].
Giải thích:
Confidence: Xác suất điều kiện, tức là xác suất một tập mặt hàng xuất hiện trong trường hợp đã biết xuất hiện của tập mặt hàng khác.
Lift: Đánh giá mức độ "độc lập" giữa các tập mặt hàng. Lift cao hơn cho thấy sự phụ thuộc mạnh mẽ hơn giữa các tập mặt hàng.