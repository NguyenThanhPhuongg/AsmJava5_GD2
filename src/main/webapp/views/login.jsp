<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dang Nhap</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
    <link rel="shortcut icon" type="image/x-icon" href="./view/images/logoyt.png">
    <style>
        body{
            background-color: white !important;
        }
    </style>
</head>
<body>

<div class="container-fluid" >
    <section class="">
        <div class="px-4 py-5 px-md-5 text-center text-lg-start" style="background-color: white">
            <div class="container">
                <div class="row gx-lg-5 align-items-center">
                    <div class="col-lg-6 mb-5 mb-lg-0">
                        <img src="https://img-angularjs.s3.amazonaws.com/productimage/3337315c272c8772de3d.jpg" width="400px" height="550px">
                    </div>
                    <div class="col-lg-6 mb-5 mb-lg-0">
                        <div class="card">
                            <div class="card-body py-5 px-md-5" style="background-color: #FFF0F5">
                                <form action="/loginAction" method="post">
                                    <div class="text-center">
                                        <h2 class="fw-bold ls-tight text-center" style="color: lightpink">
                                            Dang Nhap <br/>
                                        </h2>
                                    </div>
                                    <div class="row">
                                        <div class="form-outline mb-4">
                                            <div class="form-outline">
                                                <label class="form-label" for="form3Example1">User Name</label>
                                                <input name="username" type="text" id="form3Example1"
                                                       class="form-control"/>
                                            </div>
                                            <!-- Password input -->
                                            <div class="form-outline mb-4">
                                                <label class="form-label" for="form3Example4">Password</label>
                                                <input name="password" type="password" id="form3Example4"
                                                       class="form-control"/>
                                            </div>
                                                <% if (request.getAttribute("success") != null) { %>
                                            <div class="alert alert-success" role="alert">
                                                <%= request.getAttribute("success") %>
                                            </div>
                                                <% } %>

                                            <span style="color: red">${message}</span>
                                            <br>
                                            <!-- Submit button -->
                                            <button type="submit" class="btn btn-block mb-4"
                                                    style="background-color: lightpink">
                                                Login
                                            </button>
                                            <span class="mb-4" style="display: block">Bạn chưa có tài khoản? <a
                                                    style="color: hotpink" href="#">Singup</a></span>

                                            <!-- Register buttons -->
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Jumbotron -->
    </section>
    <!-- Section: Design Block -->
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
        crossorigin="anonymous"></script>

</body>
</html>
