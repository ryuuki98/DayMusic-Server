package like.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

import util.DBManager;

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
	
	
	public List<BoardResponseDto> printAllBoard() {
		List<BoardResponseDto> list = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT * FROM board";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int boardCode = rs.getInt(1);
				String contents = rs.getString(3);
				String id = rs.getString(2);
				String musicCode = rs.getString(4);
				boolean isPublic = rs.getBoolean(5);
				Timestamp redDate = rs.getTimestamp(6);
				
				BoardResponseDto board = new BoardResponseDto(boardCode, contents, id, musicCode, isPublic, redDate);
				list.add(board);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
}
