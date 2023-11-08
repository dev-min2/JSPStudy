<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/menu.jsp"></jsp:include>
<jsp:include page="../layout/header.jsp"></jsp:include>
	<h3>게시글 슈정화면</h3>
	<form action="modifyBoard.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="bno" value="${vo.boardNo }"/>
		<table border="1" class="table">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${vo.title}"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${vo.writer }"></td>
			</tr>
			<tr>
				<td colspan="2"><textarea cols="40" rows="5" name="content">${vo.content }</textarea></td>
			</tr>
			<tr>
				<th>파일명</th>
				<c:choose>
					<c:when test="${!empty vo.image  }">
						<td><img src="images/${vo.image }"/></td>
					</c:when>
					<c:otherwise>
						<td></td>
					</c:otherwise>
				</c:choose>
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