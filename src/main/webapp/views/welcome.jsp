<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
    <h2 style="color:green">Đăng nhập thành công</h2>

    <c:if test="${sessionScope.account != null}">
        <p>Xin chào, <b>${sessionScope.account.fullName}</b>!</p>
    </c:if>

    <form action="${pageContext.request.contextPath}/dangxuat" method="get">
        <button type="submit">Đăng xuất</button>
    </form>
</body>
</html>