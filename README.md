
# Dự Án Phân Tích Dữ Liệu Bán Lẻ (E-commerce)

## Tập Dữ Liệu

### Thuộc Tính

   1. **InvoiceNo:**
      - Số hóa đơn (Invoice Number).
      - Số định danh cho mỗi giao dịch mua sắm hoặc đơn đặt hàng.

   2. **StockCode:**
      - Mã hàng hoá (Stock Code).
      - Mã định danh cho mỗi sản phẩm trong kho hàng.

   3. **Description:**
      - Mô tả (Description).
      - Mô tả chi tiết về sản phẩm.

   4. **Quantity:**
      - Số lượng (Quantity).
      - Số lượng sản phẩm được mua hoặc bán trong mỗi giao dịch. 
      - Quantity < 0 : Đối với hóa đơn nhập hàng. 
      - Quantity > 0 : Đối với hóa đơn bán hàng

   5. **InvoiceDate:**
      - Ngày hóa đơn (Invoice Date).
      - Ngày và giờ khi giao dịch được thực hiện.

   6. **UnitPrice:**
      - Đơn giá (Unit Price).
      - Giá của mỗi sản phẩm đơn vị.

   7. **CustomerID:**
      - Định danh khách hàng (Customer ID).
      - Số định danh duy nhất cho mỗi khách hàng.

   8. **Country:**
      - Quốc gia (Country).
      - Quốc gia nơi giao dịch được thực hiện hoặc nơi mà khách hàng đặt hàng.

## Mục Đích

   Tập dữ liệu này chứa thông tin chi tiết về các giao dịch mua sắm trong lĩnh vực bán lẻ. Mỗi hàng dữ liệu đại diện cho một sản phẩm được mua bán, và các thuộc tính cung cấp thông tin hữu ích để phân tích hành vi khách hàng, quản lý kho hàng và đưa ra các quyết định kinh doanh.

## Đối Tượng Sử Dụng

   1. **Nhà Kinh Doanh:**
      - Phân tích hành vi mua sắm của khách hàng.
      - Quản lý lượng tồn kho và dự đoán nhu cầu.

   2. **Nhà Nghiên Cứu:**
      - Nghiên cứu xu hướng mua sắm và ưa thích sản phẩm.
      - Phân tích ảnh hưởng của giá cả và chính sách khuyến mãi.

   3. **Phân Tích Dữ Liệu:**
      - Xây dựng mô hình dự đoán dựa trên lịch sử giao dịch.
      - Tìm kiếm thông tin hữu ích để đưa ra quyết định kinh doanh thông minh.

## Hướng Dẫn Phân Tích Dữ Liệu

   ### Phần 1: Sử Dụng Apache Spark và Java

   1. **Yêu Cầu:**
      - Apache Spark.
      - Java Development Kit (JDK).

   2. **Phân Tích Cơ Bản:**
      -Spark SQL
      -Kmeans
      -Clustering

   ### Phần 2. Chạy lệnh: 

      1. **Upload file lên HDFS tại thư mục gốc**

      ```bash
      hdfs dfs -copyFromLocal resources/retails.csv /

      ```

      2. **Kiểm tra**

      ```bash
      hdfs dfs -ls /

      ```

      3. **Tạo file jar với Maven**

      ```
      mvn clean package
      ```

      4. **Chạy project với spark-submit:** 
      ```
      spark-submit --class com.spark.part_X.Main target/Retails-V1.jar
      ```

