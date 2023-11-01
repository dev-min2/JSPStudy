package co.yedam.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainExe {
	public static void main(String[] args) {
		Connection conn = DBConnectionPool.getInstance().getPoolConnection();
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement("select * from student");
			
			ResultSet set = pstmt.executeQuery();
			while(set.next()) {
				System.out.println("학생번호 : " + set.getString(1));
				System.out.println("이름 : " + set.getString(2));
				System.out.println("비번 : " + set.getString(3));
				System.out.println("과 : " + set.getString(4));
				System.out.println("생일 : " + set.getDate(5));
			}
			set.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		DBConnectionPool.getInstance().returnConnection(conn);
	}
}
