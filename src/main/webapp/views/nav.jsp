
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">
            <img src="https://img-angularjs.s3.amazonaws.com/productimage/3337315c272c8772de3d.jpg" width="35px" height="40px" style="margin-left: 50px"/>
            <%--                <span style="color:gray ">huh</span>--%>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/trang-chu">Heluu</a>
                </li>
            </ul>
        </div>
        <div class="col-4 d-flex justify-content-end">
            <div class="row">
                <div class="col-2 text-center mx-4">
                    <a href="${pageContext.request.contextPath}/login" class="mx-1" id="loginLink">
                        <button class="btn" style="background-color: pink">Login</button>
                    </a>
                </div>
                <div class="col-4 text-center">
                    <a href="#" class="mx-3" id="signupLink">
                        <button class="btn" style="background-color: darkgrey">Signup</button>
                    </a>
                </div>
            </div>
        </div>
    </div>
</nav>