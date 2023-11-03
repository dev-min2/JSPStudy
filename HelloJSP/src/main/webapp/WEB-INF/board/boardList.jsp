<%@page import="co.yedam.board.service.BoardVO"%>
<%@page import="co.yedam.board.serviceImpl.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판목록(board/boardList.jsp)</title>
</head>
<body>
	<h3>게시판 목록</h3>

	<%
	// jsp의 내장객체(request외 여러가지.)
	Object obj = request.getAttribute("boardList");
	List<BoardVO> vos = (List<BoardVO>) obj;
	%>
	<table border="1">
		<thead>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (BoardVO vo : vos) {
			%>
			<tr>
				<td><%=vo.getBoardNo()%></td>
				<td><a href="getBoard.do?bno=<%=vo.getBoardNo()%>"><%=vo.getTitle()%></a></td>
				<td><%=vo.getWriter()%></td>
				<td><%=vo.getWriteDate()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<p><a href="boardForm.do">등록하기</a></p>
</body>
</html>