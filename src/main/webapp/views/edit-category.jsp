<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-warning text-dark">
            <h4 class="mb-0">✏️ Chỉnh sửa danh mục</h4>
        </div>
        <div class="card-body">
            
            <!-- Hiển thị lỗi nếu có -->
            <c:if test="${not empty error}">
                <div class="alert alert-danger">${error}</div>
            </c:if>
            
            <!-- Hiển thị thông báo thành công nếu có -->
            <c:if test="${not empty success}">
                <div class="alert alert-success">${success}</div>
            </c:if>
            
            <c:url value="/admin/category/edit" var="editUrl"/>
            <form action="${editUrl}" method="post" enctype="multipart/form-data">
                <!-- Hidden ID -->
                <input type="hidden" name="id" value="${category.cateid}">

                <!-- Tên danh mục -->
                <div class="mb-3">
                    <label class="form-label fw-bold">Tên danh mục:</label>
                    <input type="text" class="form-control" name="name" 
                           value="${category.catename}" maxlength="100" required>
                </div>

                <!-- Ảnh hiện tại -->
                <div class="mb-3">
                    <label class="form-label fw-bold">Ảnh hiện tại:</label><br>
                    <c:if test="${not empty category.icon}">
                        <c:url value="/image?fname=${category.icon}" var="imgUrl"/>
                        <img src="${imgUrl}" width="120" class="img-thumbnail mb-2" alt="Category Image">
                    </c:if>
                    <c:if test="${empty category.icon}">
                        <p class="text-muted fst-italic">Chưa có ảnh</p>
                    </c:if>
                </div>

                <!-- Chọn ảnh mới -->
                <div class="mb-3">
                    <label class="form-label fw-bold">Chọn ảnh mới:</label>
                    <input type="file" class="form-control" name="icon" accept="image/*">
                </div>

                <!-- Buttons -->
                <div class="d-flex justify-content-end">
                    <a href="<c:url value='/admin/category/list'/>" class="btn btn-secondary me-2">Quay lại</a>
                    <button type="reset" class="btn btn-outline-secondary me-2">Reset</button>
                    <button type="submit" class="btn btn-primary">💾 Lưu thay đổi</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>