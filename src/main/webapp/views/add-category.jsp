<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thêm danh mục</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="card shadow-lg">
        <div class="card-header bg-primary text-white">
            <h4 class="mb-0">➕ Thêm danh mục</h4>
        </div>
        <div class="card-body">
            <form action="<c:url value='/admin/category/add'/>" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label class="form-label">Tên danh mục:</label>
                    <input type="text" class="form-control" placeholder="Nhập tên danh mục..." name="name" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Ảnh đại diện:</label>
                    <input type="file" class="form-control" name="icon" accept="image/*">
                </div>
                <div class="d-flex justify-content-end">
                    <!-- Nút Hủy quay lại danh sách -->
                    <a href="<c:url value='/admin/category/list'/>" class="btn btn-secondary me-2">❌ Hủy</a>
                    <button type="submit" class="btn btn-success">💾 Thêm</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>