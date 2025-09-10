<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Quản lý tài khoản</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="d-flex">
    <jsp:include page="/views/sidebar.jsp"/>

    <div class="container-fluid" style="margin-left:250px; padding:20px;">
        <h3 class="text-primary mb-3">👤 Quản lý tài khoản</h3>

        <a href="<c:url value='/admin/account/add'/>" class="btn btn-success mb-3">➕ Thêm tài khoản</a>

        <div class="card shadow-lg">
            <div class="card-body">
                <table class="table table-bordered table-hover text-center align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Avatar</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Vai trò</th>
                            <th>Số điện thoại</th>
                            <th>Ngày đăng ký</th>
                            <th>Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${accounts}" var="u" varStatus="stt">
                        <tr>
                            <td>${stt.index + 1}</td>
                            <td>
                                <c:if test="${u.avatar != null}">
                                    <img src="<c:url value='/image?fname=${u.avatar}'/>" 
                                         class="rounded-circle" width="50" height="50"/>
                                </c:if>
                                <c:if test="${u.avatar == null}">
                                    <img src="<c:url value='/uploads/default-avatar.png'/>" 
                                         class="rounded-circle" width="50" height="50"/>
                                </c:if>
                            </td>
                            <td>${u.userName}</td>
                            <td>${u.email}</td>
                            <td>${u.roleName}</td>
                            <td>${u.phone}</td>
                            <td>${u.createdDate}</td>
                            <td>
                                <a href="<c:url value='/admin/account/edit?id=${u.id}'/>" class="btn btn-sm btn-warning">✏️ Sửa</a>
                                <a href="<c:url value='/admin/account/delete?id=${u.id}'/>" 
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('Bạn có chắc muốn xóa tài khoản này?');">🗑️ Xóa</a>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>