package co.yedam.student.service;

import java.util.List;

public interface StudentDAO {
	public int insert(StudentVO vo);
	public int update(StudentVO vo);
	public int delete(String id);
	public StudentVO select(String id);
	public List<StudentVO> selectAll();
}
