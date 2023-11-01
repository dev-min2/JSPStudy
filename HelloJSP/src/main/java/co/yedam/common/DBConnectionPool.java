package co.yedam.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedList;
import java.util.Queue;

public class DBConnectionPool {
	private DBConnectionPool() {}
	
	private static DBConnectionPool instance = null;
	private static Object instLock = new Object();
	
	private Queue<Connection> dbConnections = new LinkedList<Connection>();
	private Object connectionLock = new Object();
	
	//jdbc
	private String dbDriver = "oracle.jdbc.OracleDriver";
	private String dbUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private String dbId = "hr";
	private String dbPwd = "1234";
	private int	dbConnectionPoolSize = 10;
	
	public static DBConnectionPool getInstance() {
		if(instance == null) {
			synchronized(instLock) {
				instance = new DBConnectionPool();
				instance.init();
			}
		}
		
		return instance;
	}
	
	public void init() {
		//dbConnections.add();
		try {
			Class.forName(dbDriver);
			for(int i = 0; i < dbConnectionPoolSize; ++i) {
				Connection conn = DriverManager.getConnection(dbUrl,dbId,dbPwd); 
				dbConnections.add(conn);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getPoolConnection() {
		// 얻을 수 있을때까지 시도한다.
		Connection conn = null;
		do {
			synchronized(connectionLock) {
				conn = dbConnections.poll();
			}
		}while(conn == null);
		
		return conn;
	}
	
	public void returnConnection(Connection conn) {
		synchronized(connectionLock) {
			dbConnections.offer(conn);
		}
	}
}
