<%@page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<head>
    <jsp:include page="../../views/hello.jsp"></jsp:include>
</head>
<body>
<h1 class="text-center">Quản Lý Hóa Đơn</h1>
<div class="container">
    <div class="d-flex justify-content-between align-items-center">
        <a href="#">
            <button class="btn btn-success"></button>
        </a>
        <form action="/hoa-don/tim-kiem" method="get">
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
        <tr>
            <th>ID</th>
            <th>Ten nhan vien</th>
            <th>Ten khach hang</th>
            <th>Ngay mua hang</th>
            <th>Trang Thai</th>
        </tr>
        <tbody>
        <c:forEach items="${listHoaDon}" var="hd">
            <tr>
                <td>${hd.id}</td>
                <td>${hd.nhanVien.ten}</td>
                <td>${hd.khachHang.ten}</td>
                <td>${hd.ngayMuaHang}</td>
                <td>${hd.trangThai == 0?"Ngung hoat dong":"Dang hoat dong"}</td>
                <td>
                    <a href="/hoa-don/edit/${hd.id}">
                        <button class="btn btn-warning">Sua</button>
                    </a>
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
</body>
</html>