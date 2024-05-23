package follow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class FollowDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private FollowDao() {}
	
	private static FollowDao instance = new FollowDao();
	
	public static FollowDao getInstance() {
		return instance;
	}
	
	public List<FollowResponseDto> findFollowingList() {
		List<FollowResponseDto> list = new ArrayList<FollowResponseDto>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT followed_id FROM follow WHERE follower_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public List<FollowResponseDto> findFollowerList() {
		List<FollowResponseDto> list = new ArrayList<FollowResponseDto>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT follower_id FROM follow WHERE followed_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
}
