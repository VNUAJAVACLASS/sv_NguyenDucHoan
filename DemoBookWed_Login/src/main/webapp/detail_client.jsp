<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sách (Client)</title>
</head>
<body>
    <c:if test="${not empty book}">
        <h2>${book.title}</h2>
        <p>
            <b>Tác giả:</b> ${book.author}
        </p>
        <p>
            <b>Giá:</b> ${book.price} VND
        </p>
        <p>
            <b>Ảnh:</b><br>
            <c:choose>
                <c:when test="${not empty book.imagePath}">
                    <img src="${pageContext.request.contextPath}/${book.imagePath}" 
                         alt="Ảnh sách" width="150">
                </c:when>
                <c:otherwise>
                    Không có ảnh
                </c:otherwise>
            </c:choose>
        </p>
    </c:if>

    <c:if test="${empty book}">
        <p>Không tìm thấy sách!</p>
    </c:if>

    <br>
    <!-- Đường link quay lại ClientHome -->
    <a href="${pageContext.request.contextPath}/clientHome">Quay lại danh sách</a>
</body>
</html>
