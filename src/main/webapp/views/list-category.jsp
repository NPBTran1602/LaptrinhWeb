<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="d-flex">
    <!-- Sidebar -->
    <jsp:include page="sidebar.jsp"/>


    <!-- Main Content -->
    <div class="container-fluid" style="margin-left:250px; padding:20px;">
        <!-- Header -->
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h3 class="text-primary">📑 Danh sách danh mục</h3>
            <div class="d-flex align-items-center">
                <span class="fw-bold me-3 text-dark">
                    👋 Xin chào, 
                    <span class="text-primary">
                        <c:out value="${sessionScope.account.userName}" default="Admin"/>
                    </span>
                </span>
                <a href="<c:url value='/dangxuat'/>" class="btn btn-sm btn-danger shadow-sm">
                    🚪 Đăng xuất
                </a>
            </div>
        </div>

        <!-- Table -->
        <div class="card shadow-lg">
            <div class="card-body">
                <table class="table table-bordered table-hover text-center align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th width="5%">#</th>
                            <th width="20%">Ảnh</th>
                            <th width="45%">Tên danh mục</th>
                            <th width="30%">Hành động</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${categories}" var="cate" varStatus="STT">
                        <tr>
                            <td>${STT.index + 1}</td>
                            <td>
                                <img src="${pageContext.request.contextPath}/image?fname=${cate.icon}" 
                                     class="img-thumbnail shadow-sm"
                                     style="width:120px; height:100px; object-fit:cover;">
                            </td>
                            <td class="fw-bold text-secondary">${cate.catename}</td>
                            <td>
                                <a href="<c:url value='/admin/category/edit?id=${cate.cateid}'/>" 
                                   class="btn btn-sm btn-warning me-1">✏️ Sửa</a>
                                <a href="<c:url value='/admin/category/delete?id=${cate.cateid}'/>" 
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('Bạn có chắc muốn xóa danh mục này?');">🗑️ Xóa</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <c:if test="${empty categories}">
                    <div class="alert alert-info text-center mt-3">
                        Chưa có danh mục nào. Hãy thêm mới!
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
