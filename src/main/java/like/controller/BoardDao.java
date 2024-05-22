package like.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BoardDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private BoardDao(){
		
	};
	
	private BoardDao instance = new BoardDao();
	
	public BoardDao getInstance() {
		return instance;
	}
	
	
}
