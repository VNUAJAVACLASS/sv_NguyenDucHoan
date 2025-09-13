<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Xử lý động tiêu đề -->
<title>${isEdit ? 'Sửa tin tức' : 'Tạo tin tức'}</title>
</head>
<body>
	<h2>${isEdit ? 'Sửa tin tức' : 'Tạo tin tức'}</h2>
	<form action="books" method="post">
		<!-- Nếu đang ở chế độ Edit, sẽ có id của tin tức cần sửa -->
		<c:if test="${isEdit}">
			<!-- Dùng thẻ ẩn hidden lưu id của tin tức
để sử dụng đến khi cập nhật CSDL về sau
-->
			<input type="hidden" name="id" value="${book.bookId}">
		</c:if>
		Tiêu đề: <br>
		<!-- Trường giá trị value sẽ rỗng nếu ứng với tình huống tạo mới tin tức -->
		<input type="text" name="title" value="${book.title}" required><br>
		<br> Nội dung: <br> <input type="text" name="author"
			value="${book.author}" required><br>
		<br> Giá: <br> <input type="text" name="price"
			value="${book.price}" required><br>
		<br> <input type="submit"
			value="${isEdit ? 'Cập nhật' : 'Tạo mới'}">
	</form>
	<br>
	<a href="books">Quay lại danh sách</a>
</body>
</html>