<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<div class="d-flex flex-column bg-primary text-white p-3" style="width:250px; height:100vh; position:fixed;">
    <h4 class="text-center mb-4">Dashboard</h4>
    
    <div class="text-center mb-3">
        <img src="<c:url value='/uploads/admin-avatar.png'/>" 
             class="rounded-circle border" width="100" height="100" alt="Admin">
        <p class="mt-2">Bạn là <strong>Admin</strong></p>
    </div>
    
    <a href="<c:url value='/admin/home'/>" class="btn btn-danger mb-2 text-start">📊 Dashboard</a>
    
    <div class="mb-2">
        <button class="btn btn-dark w-100 text-start" type="button" 
                data-bs-toggle="collapse" data-bs-target="#cateMenu">
            📂 Quản lý Danh mục
        </button>
        <div class="collapse" id="cateMenu">
            <a href="<c:url value='/admin/category/add'/>" class="d-block text-white ps-4 py-1">➕ Thêm danh mục</a>
            <a href="<c:url value='/admin/category/list'/>" class="d-block text-white ps-4 py-1">📑 Danh sách danh mục</a>
        </div>
    </div>
    
    <a href="<c:url value='/admin/product/list'/>" class="btn btn-outline-light mb-2 text-start">🛒 Quản lý sản phẩm</a>
    <a href="<c:url value='/admin/account/list'/>" class="btn btn-outline-light text-start">👤 Quản lý tài khoản</a>
</div>