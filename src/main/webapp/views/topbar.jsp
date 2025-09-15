<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Topbar</title>
</head>
<body>
<c:choose>
    <!-- Nếu chưa đăng nhập -->
    <c:when test="${sessionScope.account == null}">
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li>
                    <a href="${pageContext.request.contextPath }/login">Đăng nhập</a> |
                    <a href="${pageContext.request.contextPath }/register">Đăng ký</a>
                </li>
                <li><i class="search fa fa-search search-button"></i></li>
            </ul>
        </div>
    </c:when>

    <!-- Nếu đã đăng nhập -->
    <c:otherwise>
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li>
                    👋 Xin chào, 
                    <c:choose>
                        <c:when test="${sessionScope.account.roleid == 2}">
                            <span class="text-danger fw-bold">Quản trị viên</span>
                        </c:when>
                        <c:otherwise>
                            <span class="text-primary fw-bold">Người dùng</span>
                        </c:otherwise>
                    </c:choose>
                    - ${sessionScope.account.fullName}
                    |
                    <a href="${pageContext.request.contextPath }/logout">Đăng xuất</a>
                </li>
                <li><i class="search fa fa-search search-button"></i></li>
            </ul>

            <!-- Thông báo đăng nhập thành công -->
            <c:if test="${not empty sessionScope.message}">
                <p style="color:green; font-weight:bold; margin-top:10px">
                    ${sessionScope.message}
                </p>
                <c:remove var="message" scope="session"/>
            </c:if>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
