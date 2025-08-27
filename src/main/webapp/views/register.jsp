<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
</head>
<body>
    <form action="register" method="post">
        <h2>Tạo tài khoản mới</h2>

        <!-- Hiển thị thông báo lỗi -->
        <c:if test="${alert != null}">
            <h3 class="alert alert-danger">${alert}</h3>
        </c:if>

        <!-- Username -->
        <section>
            <div class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <input type="text" placeholder="Tài khoản" name="username" class="form-control" required>
                </div>
            </div>
        </section>

        <!-- Email -->
        <section>
            <div class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                    <input type="email" placeholder="Email" name="email" class="form-control" required>
                </div>
            </div>
        </section>

        <!-- Password -->
        <section>
            <div class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <input type="password" placeholder="Mật khẩu" name="password" class="form-control" required>
                </div>
            </div>
        </section>

        <!-- Full name -->
        <section>
            <div class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-id-card"></i></span>
                    <input type="text" placeholder="Họ tên" name="fullname" class="form-control" required>
                </div>
            </div>
        </section>

        <!-- Phone -->
        <section>
            <div class="input login-input">
                <div class="input-group">
                    <span class="input-group-addon"><i class="fa fa-phone"></i></span>
                    <input type="text" placeholder="Số điện thoại" name="phone" class="form-control" required>
                </div>
            </div>
        </section>

        <!-- Submit button -->
        <button type="submit">Đăng ký</button>
        <p>Bạn đã có tài khoản?
		    <a href="${pageContext.request.contextPath}/dangnhap">Đăng nhập</a>
		</p>
        
    </form>
</body>
</html>
