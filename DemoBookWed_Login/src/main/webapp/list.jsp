<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sach sach</title>
</head>
<body>
	<h1>Danh sach sach</h1>
	<a href="books?action=create">Them sach moi</a>
	<br>
	<br>

	<table border="1" cellpadding="5">
		<tr>
			<th>ID</th>
			<th>Tieu de</th>
			<th>Tac gia</th>
			<th>Hanh dong</th>
		</tr>

		<c:forEach var="book" items="${listbook}">
			<tr>
				<td>${book.bookId}</td>
				<td><a href="books?action=detail&id=${book.bookId}">${book.title}</a>
				</td>
				<td>
					<p>${book.author}</p>
				</td>
				<td><a href="books?action=edit&id=${book.bookId}">Sua</a>|| <a
					href="books?action=delete&id=${book.bookId}" onclick="return confirm('Xoá tin này?');">Xoá</a></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>