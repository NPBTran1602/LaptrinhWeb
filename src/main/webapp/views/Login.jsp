<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%
    String savedUser = "";
    String savedPass = "";
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie c : cookies){
            if(c.getName().equals("username")) savedUser = c.getValue();
            if(c.getName().equals("password")) savedPass = c.getValue();
        }
    }
    // Nếu có password lưu trong cookie thì đánh dấu checkbox "remember"
    boolean rememberChecked = !savedPass.isEmpty();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<style>

    .checkbox-row {
    display: flex;
    align-items: center;
    column-gap: 8px; /* khoảng cách giữa các phần tử con */
    margin: 5px 0;
    font-size: 0.85em;
}
    
    .checkbox-row input[type="checkbox"] {
        width: 14px;
        height: 14px; /* Nhỏ hơn mặc định */
    }
</style>
<script>
    function togglePassword() {
        var pwField = document.getElementById("password");
        pwField.type = pwField.type === "password" ? "text" : "password";
    }
</script>
</head>
<body>
    <div class="form-container">
        <h2>Đăng nhập</h2>

        <c:if test="${alert != null}">
            <p style="color:red; text-align:center">${alert}</p>
        </c:if>

        <form action="dangnhap" method="post">
            <input type="text" name="username" placeholder="Tài khoản" value="<%= savedUser %>" required>
            <input type="password" name="password" id="password" placeholder="Mật khẩu" value="<%= savedPass %>" required>

            <!-- Checkbox nằm dưới khung mật khẩu, căn trái và nhỏ -->
            <div class="checkbox-row">
                <input type="checkbox" name="remember" id="remember" <%= rememberChecked ? "checked" : "" %>>
                <label for="remember">Ghi nhớ mật khẩu</label>
            </div>

            <div class="checkbox-row">
                <input type="checkbox" id="showPassword" onclick="togglePassword()">
                <label for="showPassword">Hiển thị mật khẩu</label>
            </div>

            <button type="submit">Đăng nhập</button>
        </form>

        <p>Bạn chưa có tài khoản? <a href="${pageContext.request.contextPath}/register">Đăng ký</a></p>
        <p><a href="${pageContext.request.contextPath}/forgetpassword">Quên mật khẩu?</a></p>
    </div>
</body>
</html>