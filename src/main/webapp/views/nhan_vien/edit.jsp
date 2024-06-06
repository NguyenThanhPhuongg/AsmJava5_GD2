<%@page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>

<head><jsp:include page="../../views/hello.jsp"></jsp:include>
</head>
<body>
<h1 class="text-center">Quản Lý Sản Phẩm</h1>
<div class="container">
    <form action="/nhan-vien/update/${data.id}" method="post">
        <div class="mb-3">
            <label class="form-label">Ma NV</label>
            <input class="form-control" type="text" name="maNV" value="${data.maNV}" disabled>
            <c:if test="${not empty errors['maNV']}">
                <small style="color: red">${errors['maNV']}</small>
            </c:if>
        </div>
        <div class="mb-3">
            <label class="form-label">Ten</label>
            <input type="text" class="form-control" name="ten" value="${data.ten}">
            <c:if test="${not empty errors['ten']}">
                <small style="color: red">${errors['ten']}</small>
            </c:if>
        </div>
        <div class="mb-3">
            <label class="form-label">Ten dang nhap</label>
            <input class="form-control" type="text" name="tenDangNhap" value="${data.tenDangNhap}">
            <c:if test="${not empty errors['tenDangNhap']}">
                <small style="color: red">${errors['tenDangNhap']}</small>
            </c:if>
        </div>
        <div class="mb-3">
            <label class="form-label">Mat khau</label>
            <input class="form-control" type="text" name="matKhau" value="${data.matKhau}">
            <c:if test="${not empty errors['matKhau']}">
                <small style="color: red">${errors['matKhau']}</small>
            </c:if>
        </div>
        <div class="mb-3">
            <label>Trang thai</label>
            <select class="form-select" name="trangThai">
                <option value="1" ${data.trangThai == 1 ? "selected":""}>Đang hoạt động</option>
                <option value="0" ${data.trangThai == 0 ? "selected":""}>Ngừng hoạt động</option>
            </select>
        </div>
        <div class="mb-3">
            <button class="btn btn-info">Sua</button>
        </div>
    </form>
</div>
</body>
</html>