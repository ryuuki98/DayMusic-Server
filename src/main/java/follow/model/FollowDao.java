package follow.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import user.model.UserDao;
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
		List<FollowResponseDto> list = new ArrayList<>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT followed_id FROM follow WHERE follower_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, followerId);
			rs = pstmt.executeQuery();
			
			UserDao userDao = UserDao.getInstance();	
			
			while(rs.next()) {
				String id = rs.getString(1);
				String nickname = userDao.findNickNameById(id); // 아이디를 파라미터로 받아서 닉네임 반환하는 메소드
				list.add(new FollowResponseDto(nickname));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public List<FollowResponseDto> findFollowerList(String followedId) {
		List<FollowResponseDto> list = new ArrayList<>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT follower_id FROM follow WHERE followed_id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, followedId);
			rs = pstmt.executeQuery();
			
			UserDao userDao = UserDao.getInstance();	
			
			while(rs.next()) {
				String id = rs.getString(1);
				String nickname = userDao.findNickNameById(id); // 아이디를 파라미터로 받아서 닉네임 반환하는 메소드 
				list.add(new FollowResponseDto(nickname));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}
	
	public boolean addFollow(String followerId, String followedId) {
		try {
			conn = DBManager.getConnection();
			
			String sql = "INSERT INTO follow (`follower_id`, `followed_id`, `reg_date`, `mod_date`) VALUES(?, ?, NOW(), NOW())";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, followerId);
			pstmt.setString(2, followedId);
			
			pstmt.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return false;
	}
	
	public boolean deleteFollow(String followerId, String followedId) {
		try {
			conn = DBManager.getConnection();
			
			String sql = "DELETE FROM follow WHERE follower_id = ? AND followed_id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, followerId);
			pstmt.setString(2, followedId);

			pstmt.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return false;
	}

	public String followChange(String followerId, String followedId) {
		String message = "";

		try {
			FollowResponseDto check = isFollowing(followerId, followedId);
			if (check == null) {
				addFollow(followerId, followedId);
				message = "팔로우 성공";
			} else {
				deleteFollow(followerId, followedId);
				message = "팔로우 취소 성공";
			}
			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return message;
	}

	public FollowResponseDto isFollowing(String followerId, String followedId) {
		FollowResponseDto result = null;

		try {
			conn = DBManager.getConnection();
			String sql = "SELECT follower_id, followed_id FROM follow WHERE follower_id = ? AND followed_id = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, followerId);
			pstmt.setString(2, followedId);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				String refollowerId = rs.getString(1);
				String refollowedId = rs.getString(2);


				result = new FollowResponseDto(refollowerId, refollowedId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return result;
	}
}
