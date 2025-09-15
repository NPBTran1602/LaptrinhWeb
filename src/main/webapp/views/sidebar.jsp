<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- Sidebar -->
<div class="d-flex flex-column bg-primary text-white p-3" 
     style="width:250px; height:100vh; position:fixed;">

    <!-- Tiêu đề -->
    <h4 class="text-center mb-4">Dashboard</h4>
    
    <!-- Avatar + Role -->
    <div class="text-center mb-3">
        <c:choose>
            <%-- Nếu user có avatar --%>
            <c:when test="${not empty sessionScope.account.avatar}">
                <img src="<c:url value='/image?fname=${sessionScope.account.avatar}'/>" 
                     id="avatarPreview"
                     class="border"
                     alt="User Avatar">
            </c:when>
            <%-- Nếu không có avatar thì dùng ảnh mặc định --%>
            <c:otherwise>
                <img src="<c:url value='/uploads/default-avatar.png'/>" 
                     id="avatarPreview"
                     class="border"
                     alt="Default Avatar">
            </c:otherwise>
        </c:choose>

        <!-- Vai trò -->
        <p class="mt-2">
            Bạn là <strong>
                <c:choose>
                    <c:when test="${sessionScope.account.roleid == 2}">Admin</c:when>
                    <c:otherwise>Người dùng</c:otherwise>
                </c:choose>
            </strong>
        </p>
    </div>
    
    <!-- Menu -->
    <a href="<c:url value='/admin/home'/>" class="btn btn-danger mb-2 text-start">📊 Dashboard</a>
    
    <c:if test="${sessionScope.account.roleid == 2}">
        <!-- Menu danh mục -->
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
    </c:if>
</div>

<!-- CSS riêng cho avatar -->
<style>
    #avatarPreview {
        width: 100px;
        height: 100px;
        border-radius: 50%;   /* Bo tròn */
        object-fit: cover;    /* Giữ tỷ lệ ảnh */
        margin-bottom: 5px;
    }
</style>
