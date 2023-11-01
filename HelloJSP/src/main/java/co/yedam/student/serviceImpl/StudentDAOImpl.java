package co.yedam.student.serviceImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import co.yedam.common.DBConnectionPool;
import co.yedam.student.service.StudentDAO;
import co.yedam.student.service.StudentVO;

public class StudentDAOImpl implements StudentDAO {
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
	
	@Override
	public int insert(StudentVO vo) {
		String sql = "insert into student(student_id, student_name, student_password, student_dept, student_birthday) "
				+ "values (?,?,?,?,?)";
		int result = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

	@Override
	public int update(StudentVO vo) {
		String sql = "UPDATE STUDENT SET STUDENT_NAME = ?, STUDENT_PASSWORD = ?, "
				+ "STUDENT_DEPT = ?, STUDENT_BIRTHDAY = ? WHERE STUDENT_ID = ? ";
		int result = 0;

		Connection conn = DBConnectionPool.getInstance().getPoolConnection();
		PreparedStatement pstmt = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getStudentName());
			pstmt.setString(2, vo.getStudentPassword());
			pstmt.setString(3, vo.getStudentDept());
			//pstmt.setDate(4, new Date(vo.getStudentBirthday().getTime()));
			pstmt.setString(4, sdf.format(vo.getStudentBirthday()));
			pstmt.setString(5, vo.getStudentId());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		
		return result;
	}

	@Override
	public int delete(String id) {
		String sql = "DELETE FROM STUDENT WHERE STUDENT_ID = ?";
		int result = 0;

		Connection conn = DBConnectionPool.getInstance().getPoolConnection();
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		
		return result;
	}

	@Override
	public StudentVO select(String id) {
		String sql = "SELECT * FROM STUDENT WHERE STUDENT_ID = ?";
		StudentVO result = null;

		Connection conn = DBConnectionPool.getInstance().getPoolConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new StudentVO();
				
				result.setStudentId(rs.getString("STUDENT_ID"));
				result.setStudentName(rs.getString("STUDENT_NAME"));
				result.setStudentPassword(rs.getString("STUDENT_PASSWORD"));
				result.setStudentDept(rs.getString("STUDENT_DEPT"));
				result.setStudentBirthday(rs.getDate("STUDENT_BIRTHDAY"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return result;
	}

	@Override
	public List<StudentVO> selectAll() {
		String sql = "SELECT * FROM STUDENT";
		List<StudentVO> result = new ArrayList<StudentVO>();

		Connection conn = DBConnectionPool.getInstance().getPoolConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				StudentVO vo = new StudentVO();
				
				vo.setStudentId(rs.getString("STUDENT_ID"));
				vo.setStudentName(rs.getString("STUDENT_NAME"));
				vo.setStudentPassword(rs.getString("STUDENT_PASSWORD"));
				vo.setStudentDept(rs.getString("STUDENT_DEPT"));
				vo.setStudentBirthday(rs.getDate("STUDENT_BIRTHDAY"));
				
				result.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}
		
		return result;
	}
}
