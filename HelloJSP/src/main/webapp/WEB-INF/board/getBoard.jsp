<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../layout/menu.jsp" %>
<%@include file="../layout/header.jsp" %>
	<%
	BoardVO vo = (BoardVO) request.getAttribute("bno");
	%>
	<form action="modifyForm.do" name="myForm">
		<input type="hidden" name="bno" value="<%=vo.getBoardNo() %>"/>
		<table border="1" class="table">
			<tr>
				<th>글번호</th>
				<td><%=vo.getBoardNo()%></td>
				<th>작성일시</th>
				<td><%=vo.getWriteDate()%></td>
			</tr>
			<tr>
				<th>글제목</th>
				<td colspan="3"><%=vo.getTitle()%></td>
			</tr>
			<tr>
				<td colspan="4"><textarea style="width:300px; height:300px" readonly>
					<%=vo.getContent()%>
				</textarea></td>
			</tr>
			<tr>
				<th>이미지</th>
				<%
				String imageName = vo.getImage();
				if(imageName != null) { 
				%>
				<td colspan="3"><img width="150px" src="images/<%=imageName%>" /></td>
				<%} else {%>
				<td colspan="3"><img width="150px" /></td>
				<%} %>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=vo.getWriter()%></td>
				<th>조회수</th>
				<td><%=vo.getViewCnt()%></td>
			</tr>
			<tr>
			<%if(loginId == null || !vo.getWriter().equals(loginId)) { %>
				<td colspan="2"><input disabled style = "width:100%" type="submit" value="수정"></td>
				<td colspan="2"><input disabled style = "width:100%" width="100%" type="button" value="삭제"></td>
			<%} else {%>
				<td colspan="2"><input style = "width:100%" type="submit" value="수정"></td>
				<td colspan="2"><input style = "width:100%" width="100%" type="button" value="삭제"></td>
			<%} %>
			</tr>
		</table>
	</form>
	<script>
		// form submit에 대응되는 서버요청이 한개만 매핑이 되어서 이렇게한듯.
		document.querySelector('input[type=button]').addEventListener('click', (e) => {
			// 어떤기능을할지.removeForm.do!
			
			// 동적으로 Form의 action을 바꿀수도 있음.
			document.forms.myForm.action = 'removeForm.do';
			document.forms.myForm.submit();
		});
	</script>
<%@include file="../layout/footer.jsp" %>