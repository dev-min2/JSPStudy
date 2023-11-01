package co.yedam.student.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import co.yedam.common.DBConnectionPool;
import co.yedam.student.service.StudentDAO;
import co.yedam.student.service.StudentVO;

public class StudentDAOImpl implements StudentDAO {
	@Override
	public int insert(StudentVO vo) {
		String sql = "insert into student(student_id, student_name, student_password, student_dept, student_birthday) "
				+ "values (?,?,?,?,?)";

		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		try {
			conn = DBConnectionPool.getInstance().getPoolConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getStudentId());
			pstmt.setString(2, vo.getStudentName());
			pstmt.setString(3, vo.getStudentPassword());
			pstmt.setString(4, vo.getStudentDept());
			pstmt.setString(5, sdf.format(vo.getStudentBirthday()));

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		
		return result;
	}

	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				DBConnectionPool.getInstance().returnConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
