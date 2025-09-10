<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đặt lại mật khẩu</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f3f0f9;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }
    .container {
        background: #fff;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0px 4px 12px rgba(0,0,0,0.15);
        width: 380px;
        text-align: center;
    }
    h2 {
        color: #6a0dad;
        margin-bottom: 20px;
    }
    label {
        display: block;
        text-align: left;
        margin-bottom: 6px;
        font-weight: bold;
        color: #444;
    }
    input[type="password"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 18px;
        border: 1px solid #ccc;
        border-radius: 8px;
        box-sizing: border-box;
        font-size: 14px;
    }
    button {
        width: 100%;
        padding: 10px;
        background-color: #6a0dad;
        color: white;
        font-weight: bold;
        border: none;
        border-radius: 8px;
        cursor: pointer;
        transition: 0.3s;
        font-size: 14px;
    }
    button:hover {
        background-color: #550a8a;
    }
    .alert-success {
        color: green;
        margin-top: 10px;
    }
    .alert-error {
        color: red;
        margin-top: 10px;
    }
</style>
</head>
<body>
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/forgetpassword">
            <h2>Đặt lại mật khẩu cho: <span style="color:#6a0dad">${email}</span></h2>

            <input type="hidden" name="action" value="resetPassword"/>
            <input type="hidden" name="email" value="${email}"/>

            <label>Mật khẩu mới:</label>
            <input type="password" name="password" placeholder="Nhập mật khẩu mới" required/>

            <label>Nhập lại mật khẩu:</label>
            <input type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu" required/>

            <button type="submit">Cập nhật</button>

            <!-- Hiển thị thông báo -->
            <c:if test="${alert != null}">
                <p class="${alert == 'Đặt lại mật khẩu thành công' ? 'alert-success' : 'alert-error'}">${alert}</p>
            </c:if>
        </form>
    </div>
</body>
</html>