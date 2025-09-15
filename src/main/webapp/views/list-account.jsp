<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý tài khoản</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
</head>
<body>
    <div class="container">
        <h2>Danh sách tài khoản</h2>
        <table border="1" cellpadding="8" cellspacing="0" width="100%">
            <tr>
                <th>ID</th>
                <th>Tài khoản</th>
                <th>Email</th>
                <th>Họ tên</th>
                <th>SĐT</th>
                <th>Role</th>
                <th>Hành động</th>
            </tr>
            <c:forEach var="u" items="${users}">
                <tr>
                    <td>${u.id}</td>
                    <td>${u.userName}</td>
                    <td>${u.email}</td>
                    <td>${u.fullName}</td>
                    <td>${u.phone}</td>
                    <td>${u.roleId}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/user/delete?id=${u.id}" 
                           onclick="return confirm('Bạn có chắc muốn xóa tài khoản này không?')">
                           Xóa
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
