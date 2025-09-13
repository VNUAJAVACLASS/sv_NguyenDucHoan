<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Tiêu đề</th>
        <th>--</th>
    </tr>
    <c:forEach var="book" items="${bookList}">
        <tr>
            <td>${book.bookId}</td>
            <td><h4>${book.title}</h4></td>
            <td>
                <a href="clientHome?action=detail&id=${book.bookId}">Xem chi tiết</a>
            </td>
        </tr>
    </c:forEach>
</table>