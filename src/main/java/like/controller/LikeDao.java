package like.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.mysql.cj.xdevapi.PreparableStatement;

import util.DBManager;

public class LikeDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private LikeDao() {
		
	}
	private static LikeDao instance = new LikeDao();
	
	public static LikeDao getInstance() {
		return instance;
	}
	
	public List<LikeResponseDto> printAllLikeList(int boardCode){
		List<LikeResponseDto> list = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT * FROM like WHERE board_code=?";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, boardCode);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return list;
	}
}
