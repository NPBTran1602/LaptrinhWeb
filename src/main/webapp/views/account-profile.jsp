<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Quản lý tài khoản</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h3>👤 Cập nhật tài khoản</h3>
    <form method="post" enctype="multipart/form-data"
          action="${pageContext.request.contextPath}/admin/account/profile"
          class="card p-3 shadow">
          
        <div class="mb-3">
            <label>Fullname</label>
            <input type="text" name="fullname" class="form-control"
                   value="${sessionScope.account.fullName}">
        </div>
        
        <div class="mb-3">
            <label>Phone</label>
            <input type="text" name="phone" class="form-control"
                   value="${sessionScope.account.phone}">
        </div>
        
        <div class="mb-3">
            <label>Avatar</label><br>
            <c:if test="${not empty sessionScope.account.avatar}">
                <img src="${pageContext.request.contextPath}/${sessionScope.account.avatar}"
                     style="width:100px;height:100px;object-fit:cover;border-radius:50%">
            </c:if>
            <input type="file" name="avatar" class="form-control mt-2">
        </div>
        
        <div class="d-flex justify-content-between">
            <button type="submit" class="btn btn-success">💾 Lưu</button>
            <!-- nút hủy -->
            <a href="${pageContext.request.contextPath}/admin/category/list" 
               class="btn btn-secondary">❌ Hủy</a>
        </div>
    </form>
</div>
</body>
</html>
