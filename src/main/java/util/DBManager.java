package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBManager {

	public static Connection getConnection() {
		Connection conn = null;

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/daymusicDB");

			conn = ds.getConnection();

			System.out.println("DB 연동 성공");
		} catch (Exception e) {
			System.out.println("DB 연동 실패");
		}
		return conn;
	}
	
	public static void close(Connection conn , PreparedStatement pstmt, ResultSet rs) {
		try {
			conn.close();
			pstmt.close();
			rs.close();
			
			System.out.println("DB 연동 해제");
		} catch (Exception e) {
			System.out.println("DB 연동 해제 실패");
			e.printStackTrace();
		}
		
	}
	
	public static void close(Connection conn , PreparedStatement pstmt) {
		try {
			conn.close();
			pstmt.close();
			
			System.out.println("DB 연동 해제");
		} catch (Exception e) {
			System.out.println("DB 연동 해제 실패");
			e.printStackTrace();
		}
		
	}
}
