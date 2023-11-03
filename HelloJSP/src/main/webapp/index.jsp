<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>멀봐요</title>
</head>
<body>
	<%
	// boardList는 요청을 넘긴것이고(Model->View)
	// 요건 해당 페이지로 새롭게 요청하게끔 유도(request,response 유지안댐)
	response.sendRedirect("boardList.do");
	%>
</body>
</html>