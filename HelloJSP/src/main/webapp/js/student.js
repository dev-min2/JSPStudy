/**
 * student.js
 */

// document.querySelector('input[name=st_id]')
// document.querySelector('#st_name').value;
document.getElementsByName('st_submit')[0].addEventListener('click', (e) => {
	let id = document.getElementsByName('st_id')[0].value;
	let name = document.getElementsByName('st_name')[0].value;
	let pass = document.getElementsByName('st_pass')[0].value;
	let dept = document.getElementsByName('st_dept')[0].value;
	let birth = document.getElementsByName('st_birth')[0].value;

	//console.log(document.querySelector('st_id').value);

	if (id == null || name == null || pass == null || dept == null || birth == null)
		return;

	//GET은 URL에 쿼리스트링이 포함됨.
	//POST는 HTTP 메시지 Body안에 요청값이 포함됨. (content-type을 지정해야함)
	//?sId=' + id + '&sName=' + name + '&sPass=' + pass + '&sDept=' + dept + '&sBirth=' + birth

	let param = `sId=${id}&sName=${name}&sPass=${pass}&sDept=${dept}&sBirth=${birth}`;
	console.log(param);
	// application/x-www-form-urlencoded (key-value형태의 값을 전송(요청)할때 MIME-type, json도 key-value이긴한데 표현하는 형식이 틀림)
	fetch('../addStudent.do', {
		method: 'post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: param
	}).then(resolve => {
		return resolve.json();
	}).then(result => {
		console.log(result);
		if (result.retCode == "OK") {
			alert("추가완료")

			let tbody = document.querySelector('#list');
			tbody.appendChild(makeTr({
				studentId: id,
				studentName: name,
				studentPassword: pass,
				studentDept: dept,
				studentBirthday: birth
			}));
		}
		else {
			alert("추가 실패")
		}
	}).catch(reject => {
		console.log('에러 : ', reject);
	})
})


// 페로되면서 바로 실행
fetch('../studentList.do')
	.then(resolve => {
		return resolve.json();
	})
	.then(result => {
		console.log(result);

		let tbody = document.querySelector('#list');

		let tableTag = document.querySelector('table');
		result.forEach((json) => {
			tbody.appendChild(makeTr(json));
		})

	})
	.catch(reject => {
		console.log("에러에러 : ", reject);
	})

function makeTr(obj) {
	let showFields = ['studentId', 'studentName', 'studentBirthday'];

	let tr = document.createElement('tr');
	for (const prop in obj) {
		if (showFields.indexOf(prop) < 0)
			continue;

		const td = document.createElement('td');
		td.innerHTML = obj[prop];

		tr.appendChild(td);
	}

	tr.addEventListener('dblclick', showModal);
	tr.setAttribute('data-sid', obj.studentId);

	const td = document.createElement('td');
	let btn = document.createElement('button');
	btn.innerHTML = '삭제';
	btn.setAttribute('style', 'width:100%');
	btn.setAttribute('date-sid', obj.studentId); // dataset.sid으로 접근.
	btn.addEventListener('click', (e) => {

		// ajax 호출 -> 서블릿
		fetch('../delStudent.do?studentId=' + obj.studentId)
			.then(resolve => {
				return resolve.json()
			})
			.then(result => {
				if (result.retCode == "OK") {
					alert("지웠음")
					tr.remove();
				}
				else
					alert("안지워짐")
			})
			.catch(reject => {
				console.log("에러 : ", reject);
			})
	})

	td.append(btn);
	tr.append(td);
	return tr;
}

function showModal(e) {
	let trObj = this; // this는 tr
	console.log(e.target, trObj);

	let id = this.dataset.sid;

	var modal = document.getElementById("myModal");
	modal.style.display = "block";

	fetch('../getStudent.do?studentId=' + id)
		.then(resolve => resolve.json())
		.then(result => {

			if (result.retCode == "OK") {
				let resultStudent = result.student;

				modal.querySelector('h2').innerHTML = resultStudent.studentName;
				modal.querySelector('input[name=name]').value = resultStudent.studentName;
				modal.querySelector('input[name=pass]').value = resultStudent.studentPassword;
				modal.querySelector('input[name=dept]').value = resultStudent.studentDept;
				modal.querySelector('input[name=birth]').value = resultStudent.studentBirthday;
				modal.querySelector('input[name=sid]').value = resultStudent.studentId;
				//gson new GsonBuilder().setDateFormat("yyyy-MM-dd").create();로 하면됨.
				//let month = str.substring(0, monthIdx);
				//let year = str.substring(str.indexOf(',') + 1).trim();
				//let day = str.substring(monthIdx + 1, str.indexOf(',')).trim();
			}
		})
		.catch(reject => {
			console.log("에러 ..", reject);
		})



	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
}
function formatDate(date) {
	var d = new Date(date),
		month = '' + (d.getMonth()),
		day = '' + d.getDate(),
		year = d.getFullYear();

	if (month.length < 2)
		month = '0' + month;
	if (day.length < 2)
		day = '0' + day;

	return [year, month, day].join('-');
}

document.getElementById('modBtn').addEventListener('click', (e) => {
	console.log("버튼눌림");

	var modal = document.getElementById("myModal");

	let id = modal.querySelector('input[name=sid]').value;
	let name = modal.querySelector('input[name=name]').value;
	let pass = modal.querySelector('input[name=pass]').value;
	let dept = modal.querySelector('input[name=dept]').value;
	let sbirth = modal.querySelector('input[name=birth]').value;

	let param = `sId=${id}&sName=${name}&sPass=${pass}&sDept=${dept}&sBirth=${sbirth}`;

	fetch('../updStudent.do', {
		method: 'post',
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: param
	})
		.then(resolve => {
			return resolve.json();
		})
		.then(result => {
			console.log(result);
			if (result.retCode == "OK") {
				alert("수정완료")

				let findNode = {};
				for (let node of document.getElementById('list').querySelectorAll('tr')) {
					if (node.childNodes[0].innerText == id) {
						findNode = node;
						break;
					}
				}
				findNode.childNodes[0].innerHTML = id; // 어차피 그대로임융
				findNode.childNodes[1].innerHTML = name;
				findNode.childNodes[2].innerHTML = sbirth;
				// 닫아주기
				if (modal.style.display == "block")
					modal.style.display = "none";

				console.log(document.getElementById('myModal').removeAttribute('id'));
			}
			else {
				alert("수정 실패")
			}
		})
		.catch(reject => {
			console.log('에러 : ', reject);
		})
});
