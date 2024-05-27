package like.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.DBManager;

public class UserDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private UserDao() {
		
	}
	
	private static UserDao instance = new UserDao();
	
	public static UserDao getInstace() {
		return instance;
	}
	
	public String[] findUser(String id){
		String[] user = new String[2];
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT id, profile_img_url FROM users WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user[0] = rs.getString(1);
				user[1] = rs.getString(2);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return user;
	}
	
}
