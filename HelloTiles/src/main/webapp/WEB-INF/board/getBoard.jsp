<%@page import="co.yedam.board.service.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<style>
#replyList span {
	margin: 10px;
}

#replyList b {
	margin: 10px;
}

.pagination {
  display: inline-block;
}

.pagination a {
  color: black;
  float: left;
  padding: 8px 16px;
  text-decoration: none;
}

.pagination a.active {
  background-color: #4CAF50;
  color: white;
}

.pagination a:hover:not(.active) {background-color: #ddd;}
</style>

<form action="modifyForm.do" name="myForm">
	<input type="hidden" name="bno" value="${bno.boardNo }" />
	<table border="1" class="table">
		<tr>
			<th>글번호</th>
			<td id="boardNo">${bno.boardNo }</td>
			<th>작성일시</th>
			<td><fmt:formatDate value="${bno.writeDate }" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
		</tr>
		<tr>
			<th>글제목</th>
			<td colspan="3">${bno.title }</td>
		</tr>
		<tr>
			<td colspan="4"><textarea style="width: 300px; height: 300px"
					readonly>
					${bno.content }
				</textarea></td>
		</tr>
		<tr>
			<th>이미지</th>
			<c:choose>
			<c:when test="${bno.image != null }">
				<td colspan="3"><img width="150px" src="images/${bno.image }" /></td>
			</c:when>
			<c:otherwise>
				<td colspan="3"><img width="150px" /></td>
			</c:otherwise>
			</c:choose>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${bno.writer }</td>
			<th>조회수</th>
			<td>${bno.viewCnt }</td>
		</tr>
		<tr>
			<c:choose>
				<c:when test="${login == null || vo.writer != login }">
					<td colspan="2"><input disabled style="width: 100%" type="submit" value="수정"></td>
					<td colspan="2"><input disabled style="width: 100%" width="100%" type="button" value="삭제"></td>
				</c:when>
				<c:otherwise>
					<td colspan="2"><input style="width: 100%" type="submit" value="수정"></td>
					<td colspan="2"><input style="width: 100%" width="100%" type="button" value="삭제"></td>
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
</form>
<h3>댓글등록</h3>
<table class="table">
	<tr>
		<th>댓글내용</th>
		<td><input type="text" id="content" /></td>
		<td><button id="addReply">댓글등록</button></td>
	</tr>
</table>
<h3>댓글목록</h3>
<ul id="replyList">
	<li class="template" style="display: none"><span>11</span><b>첫번째 reply임둥</b><span>user01</span><span>2023-12-01</span>
	<button>삭제</button></li>
</ul>

<div class="pagination">
</div>
<script>
		// form submit에 대응되는 서버요청이 한개만 매핑이 되어서 이렇게한듯.
		document.querySelector('input[type=button]').addEventListener('click', (e) => {
			// 어떤기능을할지.removeForm.do!
			
			// 동적으로 Form의 action을 바꿀수도 있음.
			document.forms.myForm.action = 'removeForm.do';
			document.forms.myForm.submit();
		});
		
		let logID = "${login}";
		// 요렇게 해도됨 < %=vo.getBoardNo() %>
		let bno = document.querySelector('#boardNo').innerHTML;
		let page = 1;
		
		showList(1);
		function showList(pg = 1) {
			document.querySelectorAll('#replyList li:not(:nth-of-type(1))').forEach(li => li.remove());
			
			fetch('replyList.do?bno='+bno + '&page=' + pg)
			.then(resolve => {
				return resolve.json();
			})
			.then(result => {
				console.log(result);
				if(pg < 0) {
					page = Math.ceil(result.dto.total / 5);
					console.log(page);
					showList(page);
					return;
				}
				
				result.vos.forEach(reply => {
					let temp = makeRow(reply);
					document.getElementById('replyList').appendChild(temp);
				})

				makePagination(result.dto);
			})
			.catch(error => {
				console.log("에러 : ", error);
			})	
		}
		
		function makePagination(dto) {
			console.log(document.querySelector('.pagination'));
			document.querySelector('.pagination').innerHTML = '';
			
			let startPage = dto.startPage;
			let endPage = dto.endPage;
			let paginationList = document.querySelector('.pagination');
			
			if(dto.prev) {
				let aTag = document.createElement('a');
				aTag.setAttribute('href', dto.startPage-1);
				aTag.innerHTML = "&laquo;";
				paginationList.appendChild(aTag);
			}
			
			for(let start = startPage; start <= endPage; ++start) {
				let aTag = document.createElement('a');
				aTag.setAttribute('href',start);
				aTag.innerHTML = start;
				paginationList.appendChild(aTag);
				if(start == page) {
					aTag.className = 'active';
				}
			}
			
			if(dto.next) {
				let aTag = document.createElement('a');
				aTag.setAttribute('href', dto.endPage+1);
				aTag.innerHTML = "&raquo;";
				paginationList.appendChild(aTag);
			}
			
			document.querySelectorAll('.pagination a').forEach(aTag => {
				aTag.addEventListener('click', (e) => {
					e.preventDefault(); // 링크기능을 차단후 아래코드부분 실행
					
					page = aTag.getAttribute('href');
					showList(aTag.getAttribute('href'));
				})
			})
		}
		
		function makeRow(reply) {
			let template = document.querySelector('.template').cloneNode(true);
			template.style.display = "block";
			
			template.querySelector('span:nth-of-type(1)').innerHTML = reply.replyNo;
			template.querySelector('b').innerHTML = reply.reply;
			template.querySelector('span:nth-of-type(2)').innerHTML = reply.replyer;
			template.querySelector('span:nth-of-type(3)').innerHTML = reply.replyDate;
			
			template.querySelector('.template > button').addEventListener('click', (e) => {
				console.log('삭제버튼 눌림');
				console.log(e.target.parentElement);
				if(logID != reply.replyer) {
					alert("님이 작성한거 아니잖아요")
					return;
				}
				
				fetch('delReply.do?replyNo='+ reply.replyNo)
				.then(resolve => {
					return resolve.json();
				})
				.then(result => {
					if(result.retCode == "OK") {
						template.remove();
						alert("댓글삭제 성공");
						
						let liCnt = document.querySelectorAll('#replyList > li').length;
						if(liCnt <= 1 && page > 1) { // 템플릿만 남아있고 1페이지 이상이라면
							page = page-1;
							showList(page-1);
						}
						else {
							showList(page);	
						}
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
					showList(-1);
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