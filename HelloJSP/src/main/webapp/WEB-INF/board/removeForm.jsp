<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	BoardVO vo = (BoardVO)request.getAttribute("vo");
	%>
	<form action="removeBoard.do" method="post">
		<input type="hidden" name="bno" value="<%=vo.getBoardNo() %>"/>
		<table border="1">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="<%=vo.getTitle()%>"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="<%=vo.getWriter()%>"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea cols="40" rows="5" name="content"><%=vo.getContent() %></textarea></td>
			</tr>
			<tr>
				<th>파일명</th>
				<td><img src="images/<%=vo.getImage()%>"/></td>
			</tr>
			<tr>
				<td colspan="2"><input type="file" name="img" /></td>
			</tr>
			<tr>
				<td colspan="2"><input style = "width:100%" type="submit" value="정말삭제할거임?"></td> <!--<input type="reset" value="초기화"></td> --> 
			</tr>
		</table>
	</form>
</body>
</html>