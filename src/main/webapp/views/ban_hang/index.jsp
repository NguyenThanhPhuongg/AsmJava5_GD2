<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="../hello.jsp"></jsp:include>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-9">
            <a href="/ban-hang/taoHoaDon">
                <button class="btn btn-success">Tạo hóa đơn</button>
            </a>
            <hr>
            <table id="invoice-table" class="table table-bordered">
                <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên nhân viên</th>
                    <th>Tên khách hàng</th>
                    <th>Ngày mua hàng</th>
                    <th>Tong tien</th>
                    <th>Trạng thái</th>
                    <th>Thao tac</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listHD}" var="it">
                    <tr>
                        <td>${it.id}</td>
                        <td>${it.nhanVien.ten}</td>
                        <td>${it.khachHang.ten}</td>
                        <td>${it.ngayMuaHang}</td>
                        <td>${it.tongTien}</td>
                        <td>
                            <c:choose>
                                <c:when test="${it.trangThai eq 0}">
                                    Dang Cho
                                </c:when>
                                <c:when test="${it.trangThai eq 1}">
                                    Da Thanh Toan
                                </c:when>
                                <c:otherwise>
                                    DA HUY
                                </c:otherwise>

                            </c:choose>
                        </td>
                        <td>
                            <a href="/ban-hang/chonHoaDon/${it.id}">
                                <button class="btn btn-success">Chon</button>
                            </a>
                            <a href="/ban-hang/delete/${it.id}">
                                <button class="btn btn-danger">Xoa</button>
                            </a>
                        </td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
            <h5>Giỏ hàng</h5>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Mã Sản Phẩm</th>
                    <th>Tên Sản Phẩm</th>
                    <th>Kích thước</th>
                    <th>Màu sắc</th>
                    <th>Đơn Giá</th>
                    <th>Số Lượng</th>
                    <th>Tổng tiền</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cart}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.maSPCT}</td>
                        <td>${productNames[item.idSP]}</td>
                        <td>${sizeNames[item.idKT]}</td>
                        <td>${colorNames[item.idMS]}</td>
                        <td>${item.donGia}</td>
                        <td>${item.soLuong}</td>
                        <td>${item.donGia * item.soLuong}</td>
                        <td>
                            <form action="/ban-hang/remove-from-cart" method="post">
                                <input type="hidden" name="id" value="${item.id}">
                                <button type="submit" class="btn btn-danger">Xóa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-3">
            <h3>Thanh Toán</h3>
            <a href="/ban-hang/listKhachHang">
                <button class="btn btn-success">Chon khach hang</button>
            </a>
            <hr>
            <form action="/ban-hang/checkout" method="post">
                <label class="form-label">Mã Hóa Đơn: ${sessionScope.hoaDon.id} </label><br>
                <label class="form-label">SĐT: ${sessionScope.hoaDon.khachHang.sdt} </label><br>
                <label class="form-label">Tên KH: ${sessionScope.hoaDon.khachHang.ten} </label><br>
                <label class="form-label">Ngày mua: ${sessionScope.hoaDon.ngayMuaHang}</label><br>
                <label class="form-label">Tổng Tiền: ${sessionScope.hoaDon.tongTien}</label><br>
                <div class="mb-3">
                    <label class="form-label">Tiền Khách Đưa</label>
                    <input type="text" class="form-control" name="tienKhachDua">
                </div>
                <label class="form-label">Tiền Trả Lại: </label><br>
                <hr>
                <button type="submit" class="btn btn-success">Thanh Toán</button>
            </form>
        </div>
        <div class="col-md-15">
            <h5>Sản Phẩm</h5>
            <div class="d-flex justify-content-between align-items-center">
                <form action="/ban-hang/tim-kiem" method="get">
                    <div class="mt-5 d-flex">
                        <input value="${valueSearch}" class="form-control mx-2" type="text" name="valueSearch" placeholder="Nhập từ khóa">
                        <select name="searchStatus" class="form-select">
                            <option <c:if test="${searchStatus eq 1}">selected</c:if> value="1">Đang hoạt động</option>
                            <option <c:if test="${searchStatus eq 0}">selected</c:if> value="0">Ngưng hoạt động</option>
                        </select>
                        <button type="submit" class="btn btn-primary mx-2 d-flex align-items-center">Search</button>
                    </div>
                </form>
            </div>
            <table class="table">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Mã Sản Phẩm</th>
                    <th>Tên Sản Phẩm</th>
                    <th>Kích thước</th>
                    <th>Màu sắc</th>
                    <th>Đơn Giá</th>
                    <th>Số Lượng</th>
                    <th>Trạng Thái</th>
                    <th>Thao tác</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listSPCT}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.maSPCT}</td>
                        <td>${item.sanPham.ten}</td>
                        <td>${item.kichThuoc.ten}</td>
                        <td>${item.mauSac.ten}</td>
                        <td>${item.donGia}</td>
                        <td>${item.soLuong}</td>
                        <td>${item.trangThai == 1 ? "Đang hoạt động" : "Ngừng hoạt động"}</td>
                        <td>
                            <form action="/add-to-cart" method="post">
                                <input type="hidden" name="id" value="${item.id}">
                                <button type="submit" class="btn btn-success">Thêm</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item <c:if test='${currentPage == 1}'>disabled</c:if>'">
                    <a class="page-link" href="?page=1&limit=${pageSize}&valueSearch=${valueSearch}&searchStatus=${searchStatus}" aria-label="First">
                        <span aria-hidden="true">&laquo;&laquo;</span>
                    </a>
                </li>
                <li class="page-item <c:if test='${currentPage == 1}'>disabled</c:if>'">
                    <a class="page-link" href="?page=${currentPage - 1}&limit=${pageSize}&valueSearch=${valueSearch}&searchStatus=${searchStatus}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>

                <c:forEach var="i" begin="${currentPage - 2 > 0 ? currentPage - 2 : 1}" end="${currentPage + 2 < totalPages ? currentPage + 2 : totalPages}">
                    <c:if test="${i > 0 && i <= totalPages}">
                        <li class="page-item ${i == currentPage ? 'active' : ''}">
                            <a class="page-link" href="?page=${i}&limit=${pageSize}&valueSearch=${valueSearch}&searchStatus=${searchStatus}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>

                <li class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>'">
                    <a class="page-link" href="?page=${currentPage + 1}&limit=${pageSize}&valueSearch=${valueSearch}&searchStatus=${searchStatus}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <li class="page-item <c:if test='${currentPage == totalPages}'>disabled</c:if>'">
                    <a class="page-link" href="?page=${totalPages}&limit=${pageSize}&valueSearch=${valueSearch}&searchStatus=${searchStatus}" aria-label="Last">
                        <span aria-hidden="true">&raquo;&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</div>
</body>
</html>
