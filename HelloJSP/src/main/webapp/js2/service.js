/**
 * 써비쓰.js
 */

export default {
	// 목록처리
	async studentList(successCallback, errorCallback) {
		let req = await fetch('../studentList.do');
		let json = await req.json();
		try {
			successCallback(json);
		}
		catch (error) {
			errorCallback(error);
		}
	},
	// 단건조회
	async getStudent(option, successCallback, errorCallback) {
		let req = await fetch('../getStudent.do?studentId=' + option);
		let json = await req.json();
		try {
			successCallback(json);
		}
		catch (error) {
			errorCallback(error);
		}
	},
	//등록.
	async addStudent(option, successCallback, errorCallback) {
		let req = await fetch('../addStudent.do', option);
		let json = await req.json();
		try {
			successCallback(json);
		}
		catch (error) {
			errorCallback(error);
		}
	},
	//수정.
	async editStudent(option, successCallback, errorCallback) {
		let req = await fetch('../updStudent.do', option);
		let json = await req.json();
		try {
			successCallback(json);
		}
		catch (error) {
			errorCallback(error);
		}
	},
	async delStudent(option, successCallback, errorCallback) {
		let req = await fetch('../delStudent.do?studentId=' + option);
		let json = await req.json();
		try {
			successCallback(json);
		}
		catch (error) {
			errorCallback(error);
		}
	}
}
