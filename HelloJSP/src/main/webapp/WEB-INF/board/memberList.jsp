<%@page import="co.yedam.board.service.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="../layout/menu.jsp" %>
<%@include file="../layout/header.jsp" %>

<%
	List<MemberVO> vos = (List<MemberVO>)request.getAttribute("list");
%>

<table border="1" class="table">
	<thead>
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>사용자 권한</th>
		</tr>
	</thead>
	<tbody>
		<%for(int i = 0; i < vos.size(); ++i) { %>
			<tr>
				<td><%=vos.get(i).getMId() %></td>
				<td><%=vos.get(i).getPass() %></td>
				<td><%=vos.get(i).getName() %></td>
				<td><%=vos.get(i).getPhone() %></td>
				<td><%=vos.get(i).getResponsibility() %></td>
			</tr>
		<%} %>
	</tbody>
</table>

<%@include file="../layout/footer.jsp" %>