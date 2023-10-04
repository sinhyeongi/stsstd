<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록보기</title>
</head>
<body>
<table border="1">
<th>번호</th><th>제목</th><th>이름</th>
<c:forEach var="board" items="${list }">
<tr>
	<td>${board.num },</td>
	<td><a href="read?num=${board.num }">${board.subject }</a></td>
	<td>${board.name }</td>
	<td><a href="delete?num=${board.num }">삭제하기</a></td>
	<td><a href="update?num=${board.num }">수정하기</a></td>
	</tr>
	
</c:forEach>
<a href="post">글 등록</a>
</table>
</body>
</html>