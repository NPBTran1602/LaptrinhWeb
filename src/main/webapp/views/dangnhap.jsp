<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập tài khoản</title>
</head>
<body>
    <form action="dangnhap" method="post">
        <h2>Đăng nhập</h2>

        <c:if test="${alert == 'Đăng nhập thành công'}">
		    <h3 style="color:green">${alert}</h3>
		</c:if>
		<c:if test="${alert != null && alert != 'Đăng nhập thành công'}">
		    <h3 style="color:red">${alert}</h3>
		</c:if>


        <section>
            
            
            <label>Tài khoản:</label>
            <input type="text" name="username"><br><br>

            
            <label>Mật khẩu:</label>
            <input type="password" name="password"><br><br>

           
            <button type="submit">Đăng nhập</button>
        </section>
    </form>
    <p>Bạn chưa có tài khoản?
        <a href="${pageContext.request.contextPath}/register">Tạo tài khoản mới</a>
    </p>
</body>
</html>
