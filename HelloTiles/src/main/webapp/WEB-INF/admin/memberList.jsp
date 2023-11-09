<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
		<!-- VO 필드명과 동일하면 된다. -->
		<c:forEach items="${list }" var="member">
			<tr>
				<td>${member.mid}</td>
				<td>${member.pass}</td>
				<td>${member.name}</td>
				<td>${member.phone}</td>
				<td>${member.responsibility}</td>
			</tr>	
		</c:forEach>
		
		
	</tbody>
</table>