<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/menu.jsp" %>
<%@include file="../layout/header.jsp" %>
	<h3>게시글 슈정화면</h3>
	<%
	BoardVO vo = (BoardVO)request.getAttribute("vo");
	%>
	<form action="modifyBoard.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bno" value="<%=vo.getBoardNo() %>"/>
		<table border="1" class="table">
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
				<td colspan="2"><input style = "width:100%" type="submit" value="수정"></td> <!--<input type="reset" value="초기화"></td> --> 
			</tr>
		</table>
	</form>
<%@include file="../layout/footer.jsp" %>