package co.yedam.board.serviceImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import co.yedam.board.service.BoardVO;
import co.yedam.common.DBConnectionPool;
import co.yedam.student.service.StudentVO;

public class BoardDAO {
	// 목록, 단건조회, 등록, 수정, 삭제
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

	public List<BoardVO> selectAll() {
		String sql = "SELECT * FROM BOARD";
		List<BoardVO> result = new ArrayList<BoardVO>();

		Connection conn = DBConnectionPool.getInstance().getPoolConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO();

				vo.setBoardNo(rs.getInt("BOARD_NO"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setContent(rs.getString("CONTENT"));
				vo.setWriter(rs.getString("WRITER"));
				vo.setWriteDate(rs.getDate("WRITE_DATE"));
				vo.setViewCnt(rs.getInt("VIEW_CNT"));
				vo.setImage(rs.getString("IMAGE"));
				vo.setLastUpdate(rs.getDate("LAST_UPDATE"));

				result.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return result;
	}
	
	public BoardVO select(int boardNo) {
		String sql = "SELECT * FROM BOARD WHERE BOARD_NO = ?";
		BoardVO result = null;

		Connection conn = DBConnectionPool.getInstance().getPoolConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new BoardVO();

				result.setBoardNo(rs.getInt("BOARD_NO"));
				result.setTitle(rs.getString("TITLE"));
				result.setContent(rs.getString("CONTENT"));
				result.setWriter(rs.getString("WRITER"));
				result.setWriteDate(rs.getDate("WRITE_DATE"));
				result.setViewCnt(rs.getInt("VIEW_CNT"));
				result.setImage(rs.getString("IMAGE"));
				result.setLastUpdate(rs.getDate("LAST_UPDATE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return result;
	}

	public int insert(BoardVO vo) {
		String sql = "INSERT INTO BOARD VALUES (SEQ_BOARD.NEXTVAL,?,?,?,SYSDATE,?,?,SYSDATE)";
		int result = 0;

		Connection conn = DBConnectionPool.getInstance().getPoolConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getWriter());
			pstmt.setInt(4, vo.getViewCnt());
			pstmt.setString(5, vo.getImage());
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, pstmt, conn);
		}

		return result;
	}
	
	public int update(BoardVO vo) {
		String sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ?, "
				+ "IMAGE = nvl(?,image), LAST_UPDATE = sysdate WHERE BOARD_NO = ? ";
		int result = 0;

		Connection conn = DBConnectionPool.getInstance().getPoolConnection();
		PreparedStatement pstmt = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getImage());
			pstmt.setInt(4, vo.getBoardNo());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		
		return result;
	}
	
	public int delete(int boardNo) {
		String sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
		int result = 0;

		Connection conn = DBConnectionPool.getInstance().getPoolConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		
		return result;
	}
	
	public int updateViewCnt(int boardNo) {
		String sql = "UPDATE BOARD SET VIEW_CNT = VIEW_CNT + 1 WHERE BOARD_NO = ?";
		int result = 0;

		Connection conn = DBConnectionPool.getInstance().getPoolConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(null, pstmt, conn);
		}
		
		return result;
	}
}
