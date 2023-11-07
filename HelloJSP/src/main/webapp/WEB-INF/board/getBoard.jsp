<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
	#replyList span {
		margin : 10px;
	}
	#replyList b {
		margin : 10px;
	}
</style>
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
				<td id="boardNo"><%=vo.getBoardNo()%></td>
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
	<h3>댓글등록</h3>
	<table class="table">
		<tr>
			<th>댓글내용</th>
			<td><input type="text" id="content"/></td>
			<td><button id="addReply">댓글등록</button></td>
		</tr>
	</table>
	<h3>댓글목록</h3>
	<ul id="replyList">
		<li class="template" style="display:none"><span>11</span><b>첫번째 reply임둥</b><span>user01</span><span>2023-12-01</span><button>삭제</button></li>
	</ul>
	<script>
		// form submit에 대응되는 서버요청이 한개만 매핑이 되어서 이렇게한듯.
		document.querySelector('input[type=button]').addEventListener('click', (e) => {
			// 어떤기능을할지.removeForm.do!
			
			// 동적으로 Form의 action을 바꿀수도 있음.
			document.forms.myForm.action = 'removeForm.do';
			document.forms.myForm.submit();
		});
		
		let logID = '<%=loginId %>';
		// 요렇게 해도됨 < %=vo.getBoardNo() %>
		let bno = document.querySelector('#boardNo').innerHTML;
		fetch('replyList.do?bno='+bno)
		.then(resolve => {
			return resolve.json();
		})
		.then(result => {
			console.log(result);
			result.forEach(reply => {
				let temp = makeRow(reply);
				document.getElementById('replyList').appendChild(temp);
			})
		})
		.catch(error => {
			console.log("에러 : ", error);
		})
		
		function makeRow(reply) {
			let template = document.querySelector('.template').cloneNode(true);
			template.style.display = "block";
			
			template.querySelector('span:nth-of-type(1)').innerHTML = reply.replyNo;
			template.querySelector('b').innerHTML = reply.reply;
			template.querySelector('span:nth-of-type(2)').innerHTML = reply.replyer;
			template.querySelector('span:nth-of-type(3)').innerHTML = reply.replyDate;
			
			template.querySelector('.template > button').addEventListener('click', (e) => {
				console.log('삭제버튼 눌림');
				console.log(e);
				
				fetch('delReply.do?replyNo='+ reply.replyNo)
				.then(resolve => {
					return resolve.json();
				})
				.then(result => {
					if(result.retCode == "OK") {
						template.remove();
						alert("댓글삭제 성공");
					}
					else {
						alert("댓글삭제 실패");
					}
				})
				.catch(error => {
					console.log("에러 : ", error);
				})
			});
			//btn.addEventListener('click', delEventOnBtn(e));
			
			return template;
		}
		document.getElementById('addReply').addEventListener('click', (e) => {
			let reply = document.getElementById('content').value;
			document.getElementById('content').value = '';
			
			if(!bno || logID == 'null' || !reply)
				return;
			
			let param = 'bno='+bno+'&replyer=' + logID + '&reply=' + reply; 
			console.log(param);
			
			fetch('addReply.do', {
				method:'post',
				headers: {'Content-Type' : 'application/x-www-form-urlencoded'},
				body: param
			})
			.then(resolve => {
				return resolve.json();
			})
			.then(result => {
				console.log(result);
				if(result.retCode == "OK") {
					document.getElementById('replyList').appendChild(makeRow(result.vo));
				}
				else {
					alert('댓글달기 실패~');
				}
			})
			.catch(error => {
				console.log('에러 : ', error);
			})
		});
	</script>
<%@include file="../layout/footer.jsp" %>