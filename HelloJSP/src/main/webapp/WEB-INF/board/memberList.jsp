<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../layout/menu.jsp"></jsp:include>
<jsp:include page="../layout/header.jsp"></jsp:include>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['Work',     11],
          ['Eat',      2],
          ['Commute',  2],
          ['Watch TV', 2],
          ['Sleep',    7]
        ]);

        var options = {
          title: 'My Daily Activities',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
    </script>

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



<jsp:include page="../layout/footer.jsp"></jsp:include>