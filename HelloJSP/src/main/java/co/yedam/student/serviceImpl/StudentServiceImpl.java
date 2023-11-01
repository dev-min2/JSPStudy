package co.yedam.student.serviceImpl;
import java.util.List;

import co.yedam.student.service.StudentDAO;
import co.yedam.student.service.StudentService;
import co.yedam.student.service.StudentVO;

public class StudentServiceImpl implements StudentService {
	private StudentDAO dao = new StudentDAOImpl();
	
	@Override
	public boolean addStudent(StudentVO vo) {
		return dao.insert(vo) > 0;
	}

	@Override
	public boolean editStudent(StudentVO vo) {
		return dao.update(vo) > 0;
	}

	@Override
	public boolean removeStudent(String id) {
		return dao.delete(id) > 0;
	}

	@Override 
	public List<StudentVO> listStudent() {
		return dao.selectAll();
	}

	@Override
	public StudentVO getStudent(String id) {
		return dao.select(id);
	}
}