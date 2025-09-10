<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
    <div class="form-container">
        <h2>Tạo tài khoản</h2>

        <c:if test="${alert != null}">
            <p style="color:red; text-align:center">${alert}</p>
        </c:if>

        <form action="register" method="post">
            <input type="text" name="username" placeholder="Tài khoản" required>
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Mật khẩu" required>
            <input type="text" name="fullname" placeholder="Họ tên" required>
            <input type="text" name="phone" placeholder="Số điện thoại" required>
            <button type="submit">Đăng ký</button>
        </form>

        <p>Đã có tài khoản? <a href="${pageContext.request.contextPath}/dangnhap">Đăng nhập</a></p>
    </div>
</body>
</html>