package like.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private UserDao() {
		
	}
	
	private UserDao instance = new UserDao();
	
	public UserDao getInstace() {
		return instance;
	}
	
	
}
