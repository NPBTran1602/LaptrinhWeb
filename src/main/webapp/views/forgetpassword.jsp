<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quên mật khẩu</title>
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
        width: 350px;
        text-align: center;
    }
    h2 {
        color: #6a0dad;
        margin-bottom: 20px;
    }
    label {
        display: block;
        text-align: left;
        margin-bottom: 8px;
        font-weight: bold;
        color: #444;
    }
    input[type="text"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
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
        margin-bottom: 15px;
    }
    .alert-error {
        color: red;
        margin-bottom: 15px;
    }
</style>
</head>
<body>
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/forgetpassword">
		    <h2>Quên mật khẩu</h2>
		
		    <!-- Hiển thị thông báo -->
		    <c:if test="${alert != null}">
		        <p class="alert-error">${alert}</p>
		    </c:if>
		
		    <input type="hidden" name="action" value="checkEmail"/>
		
		    <label>Tên đăng nhập:</label>
		    <input type="text" name="username" placeholder="Nhập tên đăng nhập" required>
		
		    <label>Email:</label>
		    <input type="text" name="email" placeholder="Nhập email gắn với tài khoản" required>
		
		    <button type="submit">Xác nhận</button>
		</form>


    </div>
</body>
</html>