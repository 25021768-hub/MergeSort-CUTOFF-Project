
# TỐI ƯU HÓA MERGE SORT BẰNG INSERTION SORT CUTOFF

## 1. Giới thiệu dự án

Dự án thực hiện thí nghiệm đánh giá hiệu năng của thuật toán Merge Sort khi áp dụng kỹ thuật CUTOFF.

Ý tưởng của phương pháp này là sử dụng Insertion Sort để sắp xếp các mảng con có kích thước nhỏ, thay vì tiếp tục chia nhỏ và đệ quy bằng Merge Sort.

Dự án so sánh ba cấu hình:

- **CUTOFF = 0:** Merge Sort cơ bản, không sử dụng Insertion Sort.
- **CUTOFF = 7:** Sử dụng Insertion Sort khi kích thước mảng con nhỏ hơn hoặc bằng 7.
- **CUTOFF = 10:** Sử dụng Insertion Sort khi kích thước mảng con nhỏ hơn hoặc bằng 10.

Mục tiêu là xác định giá trị CUTOFF cho hiệu năng tốt nhất trong ba cấu hình trên đối với máy tính đang sử dụng.

## 2. Môi trường thực nghiệm

- Ngôn ngữ lập trình: Java
- Phiên bản JDK: Java 21.0.12.1
- IDE: IntelliJ IDEA
- Hệ điều hành: Windows 11
- CPU: Intel Core i7-13620H
- RAM: 16 GB

## 3. Cấu trúc dự án

```text
MergeSort-CUTOFF-Project/
│
├── src/
│   └── sorting/
│       ├── InsertionSort.java
│       ├── MergeSort.java
│       ├── DataGenerator.java
│       ├── Benchmark.java
│       └── Main.java
│
├── datasets/
│   ├── dataset_1000.csv
│   ├── dataset_10000.csv
│   ├── dataset_100000.csv
│   ├── dataset_500000.csv
│   └── dataset_1000000.csv
│
├── results/
│   ├── benchmark_results.csv
│   ├── benchmark_results_run1.csv
│   ├── benchmark_results_run2.csv
│   ├── benchmark_results_run3.csv
│   ├── MergeSort_Benchmark_KetQuaMoi.xlsx
│   └── MergeSort_BieuDo.pdf
│
├── .gitignore
└── README.md
```

## 4. Chức năng các thành phần

### Các file mã nguồn

- **InsertionSort.java:** Cài đặt thuật toán Insertion Sort, hỗ trợ sắp xếp toàn bộ mảng hoặc một đoạn mảng.
- **MergeSort.java:** Cài đặt thuật toán Merge Sort và tích hợp kỹ thuật CUTOFF.
- **DataGenerator.java:** Tạo các bộ dữ liệu số nguyên ngẫu nhiên và xuất chúng ra file CSV.
- **Benchmark.java:** Thực hiện đo thời gian chạy, kiểm tra tính đúng đắn và lưu kết quả thực nghiệm.
- **Main.java:** Chương trình chính dùng để kiểm tra thuật toán.

### Các thư mục

- **datasets/:** Chứa năm bộ dữ liệu đầu vào với kích thước khác nhau.
- **results/:** Chứa kết quả đo thời gian, dữ liệu tổng hợp, bảng phân tích và biểu đồ.

## 5. Dữ liệu thực nghiệm

Thí nghiệm sử dụng năm bộ dữ liệu có kích thước:

| Bộ dữ liệu | Số phần tử |
|---|---:|
| Dataset 1 | 1.000 |
| Dataset 2 | 10.000 |
| Dataset 3 | 100.000 |
| Dataset 4 | 500.000 |
| Dataset 5 | 1.000.000 |

Các phần tử là số nguyên ngẫu nhiên trong khoảng từ 0 đến 999.999.

Chương trình sử dụng seed cố định bằng 12345 để đảm bảo có thể tái tạo cùng một bộ dữ liệu.

## 6. Phương pháp đo hiệu năng

Với mỗi kích thước dữ liệu, chương trình tiến hành so sánh ba cấu hình CUTOFF = 0, 7 và 10.

Quy trình thực nghiệm:

1. Thực hiện 5 vòng khởi động (warm-up) cho mỗi kích thước dữ liệu và cấu hình.
2. Tiến hành 20 lần đo cho mỗi cấu hình.
3. Sử dụng cùng dữ liệu đầu vào để so sánh công bằng.
4. Tạo bản sao của mảng trước khi đo thời gian.
5. Sử dụng System.nanoTime() để đo thời gian thực thi.
6. Kiểm tra tính đúng đắn của kết quả sau khi sắp xếp.
7. Sử dụng trung vị (median) của 20 lần đo làm kết quả đại diện.

Thời gian thực thi được quy đổi sang đơn vị mili giây (ms).

Lưu ý: Benchmark.java hiện tạo dữ liệu trực tiếp trong bộ nhớ bằng seed cố định. Các file CSV trong datasets/ được DataGenerator.java xuất riêng để lưu trữ và kiểm tra dữ liệu đầu vào.

## 7. Kết quả thực nghiệm

Kết quả đo được lưu trong thư mục results/.

Các file chính:

- **benchmark_results_run1.csv:** Kết quả lần chạy thứ nhất.
- **benchmark_results_run2.csv:** Kết quả lần chạy thứ hai.
- **benchmark_results_run3.csv:** Kết quả lần chạy thứ ba.
- **benchmark_results.csv:** Kết quả benchmark được chương trình xuất ra.
- **MergeSort_Benchmark_KetQuaMoi.xlsx:** Bảng tổng hợp, phân tích và so sánh hiệu năng.
- **MergeSort_BieuDo.pdf:** Các biểu đồ so sánh thời gian thực thi.

Kết quả tổng hợp cho thấy CUTOFF = 10 có thời gian thực thi trung vị thấp nhất ở cả năm kích thước dữ liệu trong phạm vi thí nghiệm.

Phân tích chi tiết được trình bày trong file Excel và báo cáo thí nghiệm riêng.

## 8. Hướng dẫn chạy chương trình

### Bước 1: Tạo dữ liệu

Mở file DataGenerator.java và chạy phương thức main().

Chương trình sẽ tạo năm file CSV trong thư mục datasets/.

### Bước 2: Thực hiện benchmark

Mở file Benchmark.java và chạy phương thức main().

Chương trình sẽ tự động:

- Khởi động JVM bằng các vòng warm-up.
- Thực hiện các lần đo cho ba cấu hình CUTOFF.
- Kiểm tra kết quả sắp xếp.
- Tính thời gian thực thi trung vị.
- Xuất kết quả vào thư mục results/.

### Bước 3: Xem kết quả

Mở file MergeSort_Benchmark_KetQuaMoi.xlsx để xem bảng tổng hợp và biểu đồ.

Có thể mở file PDF trong thư mục results/ để xem các biểu đồ đã xuất.

## 9. Kết luận

Dự án thực hiện so sánh ba cấu hình Merge Sort với CUTOFF = 0, 7 và 10 trên năm kích thước dữ liệu khác nhau.

Trong phạm vi các cấu hình, dữ liệu và môi trường đã thử nghiệm, CUTOFF = 10 cho kết quả tốt nhất về thời gian thực thi trung vị.

Tuy nhiên, giá trị CUTOFF tối ưu có thể thay đổi tùy thuộc vào phần cứng, môi trường chạy Java, cách cài đặt thuật toán và đặc điểm dữ liệu đầu vào.
