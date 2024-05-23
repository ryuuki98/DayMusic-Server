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
	
	public List<FollowResponseDto> findFollowingList(String followerId) {
		List<FollowResponseDto> list = new ArrayList<FollowResponseDto>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT followed_id FROM follow WHERE follower_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, followerId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public List<FollowResponseDto> findFollowerList(String followedId) {
		List<FollowResponseDto> list = new ArrayList<FollowResponseDto>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT follower_id FROM follow WHERE followed_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, followedId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
	public boolean addFollow(FollowRequestDto frqDto) {
		try {
			conn = DBManager.getConnection();
			
			String sql = "INSERT INTO (`follower_id`, `followed_id`, `reg_date`, `mod_date`) VALUES(?, ?, NOW(), NOW())";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, frqDto.getFollowedId());
			pstmt.setString(2, frqDto.getFollowerId());
			
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return false;
	}
	
	public boolean deleteFollow(FollowRequestDto frqDto) {
		try {
			conn = DBManager.getConnection();
			
			String sql = "DELETE FROM follow WHERE followed_id = ? AND follower_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, frqDto.getFollowedId());
			pstmt.setString(2, frqDto.getFollowerId());
			
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return false;
	}
}
