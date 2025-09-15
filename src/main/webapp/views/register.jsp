<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng ký tài khoản</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"/>
<style>
    body {
        font-family: Arial, sans-serif;
        background: linear-gradient(to right, #6dd5fa, #2980b9);
    }
    .form-container {
        max-width: 1000px;
        margin: 50px auto;
        padding: 30px;
        background: #fff;
        border-radius: 10px;
        box-shadow: 0px 0px 10px rgba(0,0,0,0.1);
    }
    .form-container h2 {
        text-align: center;
        margin-bottom: 20px;
    }
    .form-grid {
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 20px 30px;
    }
    .form-grid input,
    .form-grid select {
        width: 100%;
        height: 50px;
        padding: 12px 15px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 6px;
        box-sizing: border-box;
    }
    .form-grid input[type="file"] {
        padding: 10px;
        background: #f9f9f9;
        height: auto;
    }
    .form-grid .file-name {
        margin-top: 5px;
        font-size: 14px;
        color: #555;
    }
    /* Avatar block chiếm trọn 2 cột */
    .avatar-block {
	    grid-column: span 2;
	    display: flex;
	    align-items: center;
	    gap: 15px;
	    justify-content: center; /* canh giữa */
	}
	#avatarPreview {
	    display: none;
	    width: 100px;
	    height: 100px;
	    border-radius: 50%;
	    object-fit: cover;
	    border: 1px solid #ccc;
	}

    .form-submit {
        grid-column: span 2;
        text-align: center;
    }
    .form-submit button {
        width: 100%;
        padding: 15px;
        background: #007bff;
        border: none;
        border-radius: 5px;
        color: white;
        font-size: 16px;
        cursor: pointer;
    }
    .form-submit button:hover {
        background: #0056b3;
    }
    .alert {
        color: red;
        text-align: center;
        margin-bottom: 10px;
    }
</style>
</head>
<body>
    <div class="form-container">
        <h2>Tạo tài khoản</h2>

        <c:if test="${alert != null}">
            <p class="alert">${alert}</p>
        </c:if>

        <form action="register" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
            <div class="form-grid">
                <input type="text" name="fullname" placeholder="Họ tên" required>
                <input type="text" name="username" placeholder="Tên đăng nhập" required>

                <input type="email" name="email" placeholder="Email" required>
                <input type="password" name="password" placeholder="Mật khẩu" required>

                <input type="password" name="repassword" placeholder="Nhập lại mật khẩu" required>
                <input type="text" name="phone" placeholder="Số điện thoại" required>

                <select name="roleid" required>
                    <option value="1">Người dùng</option>
                    <option value="2">Quản trị viên</option>
                </select>

                <!-- Avatar: chọn file từ máy + preview -->
                <div class="avatar-block">
                    <div style="flex:1;">
                        <input type="file" name="avatar" id="avatarInput" accept="image/*">
                        <div class="file-name" id="fileName"></div>
                    </div>
                    <img id="avatarPreview" src="#" alt="Avatar Preview"/>
                </div>

                <div class="form-submit">
                    <button type="submit">Đăng ký</button>
                </div>
            </div>
        </form>

        <p style="text-align:center; margin-top:10px;">
            Đã có tài khoản? 
            <a href="${pageContext.request.contextPath}/dangnhap">Đăng nhập</a>
        </p>
    </div>

    <script>
        document.getElementById("avatarInput").addEventListener("change", function(event) {
            const [file] = event.target.files;
            const fileNameDiv = document.getElementById("fileName");
            if (file) {
                fileNameDiv.textContent = 'Selected file: ' + file.name;
                const preview = document.getElementById("avatarPreview");
                preview.src = URL.createObjectURL(file);
                preview.style.display = "block";
            } else {
                fileNameDiv.textContent = '';
                document.getElementById("avatarPreview").style.display = "none";
            }
        });

        function validateForm() {
            const fileInput = document.getElementById('avatarInput');
            if (fileInput.files.length === 0) {
                alert('Vui lòng chọn ảnh đại diện.');
                return false;
            }
            return true;
        }
    </script>
</body>
</html>
