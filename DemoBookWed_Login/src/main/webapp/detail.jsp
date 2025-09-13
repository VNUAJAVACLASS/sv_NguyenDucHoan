<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>${book.title}</h2>
<p>${book.author}</p>
<p>${book.price}</p>

<br>
<a href="${pageContext.request.contextPath}/books">Quay lại danh sách</a>

</body>
</html>