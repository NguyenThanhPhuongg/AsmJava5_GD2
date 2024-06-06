<%@page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

<head><jsp:include page="../../views/hello.jsp"></jsp:include>
</head>
<body>
<h1 class="text-center">Quản Lý Hoa Don</h1>
<div class="container">
    <form action="/hoa-don/update/${data.id}" method="post">
        <div class="mb-3">
            <label class="form-label">Nhan Vien</label>
            <select class="form-select" name="idNhanVien" >
                <c:forEach items="${listNhanVien}" var="nhanVien">
                    <option value="${nhanVien.id}">${nhanVien.ten}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Khach Hang</label>
            <select class="form-select" name="idKhachHang" aria-readonly="true">
                <c:forEach items="${listKhachHang}" var="khachHang">
                    <option value="${khachHang.id}">${khachHang.ten}</option>
                </c:forEach>
            </select>
        </div>
<%--        <div class="mb-3">--%>
<%--            <label class="form-label">Ngay mua hang</label>--%>
<%--                <input type="text" class="form-control" name="ngayMuaHang" value="${data.ngayMuaHang}" readonly>--%>
<%--        </div>--%>
        <div class="mb-3">
            <label>Trang thai</label>
            <select class="form-select" name="trangThai">
                <option value="1" ${data.trangThai == 1 ? "selected" : ""}>Đang hoạt động</option>
                <option value="0" ${data.trangThai == 0 ? "selected" : ""}>Ngừng hoạt động</option>
            </select>
        </div>
        <div class="mb-3">
            <button class="btn btn-info">update</button>
        </div>
    </form>
</div>
</body>
</html>