<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hồ sơ cá nhân</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<style>
    body {
        font-family: Arial, sans-serif;
        background: #f3f0f9;
        margin: 0;
        padding: 0;
    }
    .profile-container {
        max-width: 600px;
        margin: 40px auto;
        padding: 30px;
        background: #fff;
        border-radius: 12px;
        box-shadow: 0px 4px 12px rgba(0,0,0,0.15);
    }
    h2 {
        text-align: center;
        color: #6a0dad;
        margin-bottom: 20px;
    }
    .form-group {
        margin-bottom: 20px;
    }
    label {
        display: block;
        margin-bottom: 8px;
        font-weight: bold;
        color: #444;
    }
    input[type="text"], input[type="file"] {
        width: 100%;
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 8px;
        font-size: 14px;
        box-sizing: border-box;
    }
    .avatar-preview {
        display: block;
        margin: 15px auto;
        width: 120px;
        height: 120px;
        border-radius: 50%;
        object-fit: cover;
        border: 2px solid #ddd;
    }
    button {
        width: 100%;
        padding: 12px;
        background-color: #6a0dad;
        color: white;
        border: none;
        border-radius: 8px;
        font-size: 15px;
        cursor: pointer;
        font-weight: bold;
    }
    button:hover {
        background-color: #550a8a;
    }
    .alert {
        text-align: center;
        margin-bottom: 15px;
        font-weight: bold;
    }
    .alert-success { color: green; }
    .alert-error { color: red; }
</style>
<script>
    function previewAvatar(event) {
        const [file] = event.target.files;
        if (file) {
            const preview = document.getElementById("avatarPreview");
            preview.src = URL.createObjectURL(file);
            preview.style.display = "block";
        }
    }
</script>
</head>
<body>
    <div class="profile-container">
        <h2>Cập nhật hồ sơ</h2>

        <!-- Thông báo -->
        <c:if test="${alert != null}">
            <p class="alert ${alert eq 'Cập nhật thành công!' ? 'alert-success' : 'alert-error'}">${alert}</p>
        </c:if>

        <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
            
            <!-- Avatar -->
            <div class="form-group" style="text-align:center;">
                <img id="avatarPreview"
                     src="${pageContext.request.contextPath}/uploads/${account.avatar}"
                     alt="Avatar" class="avatar-preview"/>
                <input type="file" name="avatar" accept="image/*" onchange="previewAvatar(event)">
            </div>

            <!-- Fullname -->
            <div class="form-group">
                <label for="fullname">Họ tên:</label>
                <input type="text" id="fullname" name="fullname" value="${account.fullname}" required>
            </div>

            <!-- Phone -->
            <div class="form-group">
                <label for="phone">Số điện thoại:</label>
                <input type="text" id="phone" name="phone" value="${account.phone}" required>
            </div>

            <button type="submit">Cập nhật</button>
        </form>
    </div>
</body>
</html>
