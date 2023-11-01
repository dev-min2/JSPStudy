package co.yedam.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import co.yedam.student.service.StudentService;
import co.yedam.student.service.StudentVO;
import co.yedam.student.serviceImpl.StudentServiceImpl;

public class MainExe {
	public static void main(String[] args) throws ParseException {
		StudentService svc = new StudentServiceImpl();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StudentVO vo = new StudentVO();
		vo.setStudentId("minkk");
		vo.setStudentName("킹민교2");
		vo.setStudentPassword("123456");
		vo.setStudentDept("건축공학과");
		vo.setStudentBirthday(sdf.parse("2001-05-05"));
		
		List<StudentVO> vo2 =svc.listStudent();
//		
//		vo2.stream().forEach((obj) -> {
//			System.out.println(obj);
//		});
		
		svc.editStudent(vo);
		
	}
}
