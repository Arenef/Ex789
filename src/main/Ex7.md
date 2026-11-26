# Bài tập Chương 7 -- Cách làm việc với Session và Cookie

## Exercise 7-1 -- Sử dụng Cookie

1.  Mở project `ch07_ex1_download` trong thư mục `ex_starts`.

2.  Chạy project. Thao tác này sẽ hiển thị một danh sách các liên kết
    album.

3.  Nhấp vào liên kết của một album. Thao tác này sẽ hiển thị trang
    Register (Đăng ký).

4.  Sử dụng trang Register để đăng ký. Thao tác này sẽ đưa bạn đến trang
    Downloads (Tải xuống), nơi bạn có thể nghe một tệp MP3.

    Khi bạn nhấp vào một liên kết MP3 trên trang Downloads, trình duyệt
    của bạn sẽ phát tệp âm thanh tương ứng. Để việc này hoạt động, ứng
    dụng Music Store phải được triển khai (deploy). Nếu chưa được triển
    khai, bạn sẽ nhận được lỗi 404 khi nhấp vào liên kết MP3. Để khắc
    phục, bạn có thể mở project `musicStore` trong thư mục `book_apps`
    và chạy project đó.

5.  Từ trang Downloads, nhấp vào liên kết View All Cookies (Xem tất cả
    Cookie). Thao tác này sẽ hiển thị tất cả cookie mà ứng dụng này đã
    gửi đến trình duyệt của bạn. Trong danh sách này phải có cookie tên
    `emailCookie` và một cookie tên `JSESSIONID`.

6.  Nhấp vào liên kết Delete All Persistent Cookies (Xóa tất cả Cookie
    tồn tại lâu dài) để xóa tất cả cookie tồn tại lâu dài.

7.  Đóng trình duyệt và chạy lại ứng dụng. Sau đó, nhấp vào một liên kết
    album. Thao tác này sẽ hiển thị lại trang Register.

8.  Sử dụng trang Register để đăng ký một lần nữa.

9.  Mở mã nguồn của lớp `DownloadServlet`. Sau đó, sửa đổi đoạn mã thêm
    cookie để cookie được lưu trữ với tên `userEmail`. Đồng thời, sửa
    đổi đoạn mã này để cookie được lưu trữ trong 3 năm.

10. Mở tệp `index.jsp` và thêm mã vào đầu trang để hiển thị tên và giá
    trị của cookie. Đối với địa chỉ email `jsmith@gmail.com`, đoạn mã
    này sẽ hiển thị kết quả sau:

    ``` text
    User Email: jsmith@gmail.com
    ```

    Để thực hiện việc này, bạn có thể thêm một thẻ `p` vào trang, sử
    dụng đối tượng ngầm định `cookie` của EL để lấy giá trị của cookie
    lưu trữ địa chỉ email của người dùng.

11. Chạy lại project. Sau đó, kiểm tra để bảo đảm project hoạt động
    chính xác. Để thực hiện việc này, bạn có thể đăng ký, sử dụng liên
    kết View All Cookies để xem tên và giá trị của cookie này, rồi sử
    dụng trang Index để xem giá trị của cookie email.

------------------------------------------------------------------------

## Exercise 7-2 -- Sử dụng thuộc tính Session

1.  Mở project `ch07_ex2_download` trong thư mục `ex_starts`.

2.  Mở mã nguồn của lớp `DownloadServlet`. Sau đó, trong phương thức
    `checkUser`, sửa đổi mã để đối tượng `Product` được lưu trữ dưới
    dạng thuộc tính của session thay vì lưu trữ mã sản phẩm dưới dạng
    thuộc tính của session. Để thực hiện việc này, bạn có thể tạo một
    đối tượng `Product` lưu trữ dữ liệu cho sản phẩm hiện tại bằng
    phương thức `getProduct` của lớp `murach.data.ProductIO`, như sau:

    ``` java
    ServletContext sc = this.getServletContext();
    String productPath = sc.getRealPath("/WEB-INF/products.txt");
    Product product = ProductIO.getProduct(code, productPath);
    ```

3.  Trong phương thức `registerUser`, sửa đổi mã để sử dụng đối tượng
    `Product` đang được lưu trong session nhằm xây dựng URL cho trang
    Downloads phù hợp.

4.  Đối với trang Downloads của từng sản phẩm, lấy đối tượng `Product`
    từ session và sử dụng đối tượng đó để lấy mã sản phẩm cũng như phần
    mô tả của album.

5.  Chạy ứng dụng Download và kiểm tra để bảo đảm ứng dụng hoạt động
    chính xác. Để các liên kết MP3 hoạt động chính xác, bạn có thể cần
    mở project `musicStore` trong thư mục `book_apps` và chạy project
    đó.

6.  Xem các cookie của ứng dụng này. Sau đó, nhấp vào liên kết Delete
    All Persistent Cookies (Xóa tất cả Cookie tồn tại lâu dài) để xóa
    tất cả cookie tồn tại lâu dài.

7.  Đóng trình duyệt và chạy lại ứng dụng Download để bảo đảm lớp
    `DownloadServlet` vẫn hoạt động chính xác.

------------------------------------------------------------------------

## Exercise 7-3 -- Sử dụng URL rewriting thay cho các hidden field

1.  Mở project `ch07_ex3_cart` trong thư mục `ex_starts`.
2.  Mở mã nguồn của lớp `CartServlet`. Lưu ý rằng servlet này hiện chỉ
    hỗ trợ phương thức HTTP POST.
3.  Mở tệp `cart.jsp`. Sau đó, chuyển nút Remove (Xóa) thành một liên
    kết Remove sử dụng kỹ thuật URL rewriting.
4.  Chạy ứng dụng và kiểm tra liên kết Remove. Liên kết này sẽ không
    hoạt động vì nó sử dụng phương thức HTTP GET.
5.  Sửa đổi lớp `CartServlet` để bổ sung phương thức `doGet`, trong đó
    có một câu lệnh duy nhất gọi phương thức `doPost`. Bằng cách đó, lớp
    `CartServlet` sử dụng cùng một mã để xử lý cả phương thức HTTP POST
    và GET.
6.  Chạy lại ứng dụng và kiểm tra liên kết Remove. Lúc này, liên kết sẽ
    hoạt động chính xác và hiển thị tên cùng giá trị của các tham số
    trong URL của trình duyệt.
7.  Kiểm tra cả nút Update (Cập nhật). Vì nút này sử dụng phương thức
    HTTP POST, nó sẽ không hiển thị tên và giá trị của các tham số trong
    URL của trình duyệt.
