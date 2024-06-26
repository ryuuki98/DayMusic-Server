package user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;
import util.PasswordCrypto;

public class UserDao {
	private UserDao() {
	}

	private static UserDao instance = new UserDao();

	public static UserDao getInstance() {
		return instance;
	}

	public UserResponseDto validateUser(UserRequestDto userRequestDto) {
		UserResponseDto user = findUserById(userRequestDto.getId());

		String encryptPassword = user.getPassword();

		
		if (!PasswordCrypto.decrypt(userRequestDto.getPassword(), encryptPassword)) {
			
			user = null;
		}

		return user;
	}

	public UserResponseDto joinUser(UserRequestDto userRequestDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		UserResponseDto user = null;

		String sql = "INSERT INTO users (id,password,name,gender,email,phone,telecom,nickname,profile_img_url,is_staff) values (?,?,?,?,?,?,?,?,?,?)";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userRequestDto.getId());
			pstmt.setString(2, PasswordCrypto.encrypt(userRequestDto.getPassword()));
			pstmt.setString(3, userRequestDto.getName());
			pstmt.setString(4, userRequestDto.getGender());
			pstmt.setString(5, userRequestDto.getEmail());
			pstmt.setString(6, userRequestDto.getPhone());
			pstmt.setString(7, userRequestDto.getTelecom());
			pstmt.setString(8, userRequestDto.getNickname());
			pstmt.setString(9, userRequestDto.getProfileImgUrl());
			pstmt.setBoolean(10, userRequestDto.isIs_staff());

			pstmt.execute();

			user = findUserById(userRequestDto.getId());

		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return user;
	}

	public UserResponseDto findUserById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserResponseDto user = null;
		String sql = "SELECT id,password,name,gender,email,phone,telecom,nickname,profile_img_url,is_staff FROM users WHERE id = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String password = rs.getString(2);
				String name = rs.getString(3);
				String gender = rs.getString(4);
				String email = rs.getString(5);
				String phone = rs.getString(6);
				String telecom = rs.getString(7);
				String nickname = rs.getString(8);
				String profile_img_url = rs.getString(9) == null ? "" : rs.getString(9);
				boolean is_staff = rs.getBoolean(10);

				user = new UserResponseDto(id, password, name, gender, email, phone, telecom, nickname, profile_img_url, is_staff);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public boolean duplicateNickname(String id, String newNickname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isDuplicated = false;
		String sql = "SELECT COUNT(*) FROM users WHERE id != ? AND nickname = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, newNickname);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					isDuplicated = true;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return isDuplicated;
	}

	public boolean duplicatePhone(String id, String newPhone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isDuplicated = false;
		String sql = "SELECT COUNT(*) FROM users WHERE id != ? AND phone = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, newPhone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					isDuplicated = true;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return isDuplicated;
	}

	public boolean duplicateEmail(String id, String newEmail) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isDuplicated = false;
		String sql = "SELECT COUNT(*) FROM users WHERE id != ? AND email = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, newEmail);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				if (count > 0) {
					isDuplicated = true;
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return isDuplicated;
	}

	public UserResponseDto updateNickname(String id, String newNickname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		UserResponseDto user = null;
		String sql = "UPDATE users SET nickname = ? WHERE id = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newNickname);
			pstmt.setString(2, id);
			pstmt.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		user = findUserById(id);
		return user;
	}

	public UserResponseDto updatePassword(String id, String newPassword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		UserResponseDto user = null;
		String sql = "UPDATE users SET password = ? WHERE id = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, PasswordCrypto.encrypt(newPassword));
			pstmt.setString(2, id);
			pstmt.execute();

			user = findUserById(id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return user;
	}

	public UserResponseDto updateProfileImage(String id, String profile_img_url) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		UserResponseDto user = null;

		
		

		String sql = "UPDATE users SET profile_img_url = ? WHERE id = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, profile_img_url);
			pstmt.setString(2, id);
			pstmt.execute();

			user = findUserById(id);
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return user;
	}

	public UserResponseDto updateInformation(UserRequestDto userRequestDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		UserResponseDto user = null;

		String id = userRequestDto.getId();
		String newEmail = userRequestDto.getEmail();
		String newPhone = userRequestDto.getPhone();

		// unique key에 대한 중복 검증
		if (duplicateEmail(id, newEmail) || duplicatePhone(id, newPhone)) {
			return user;
		}

		String sql = "UPDATE users SET name = ?, gender = ? ,email = ?,phone = ? ,telecom = ? WHERE id = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userRequestDto.getName());
			pstmt.setString(2, userRequestDto.getGender());
			pstmt.setString(3, userRequestDto.getEmail());
			pstmt.setString(4, userRequestDto.getPhone());
			pstmt.setString(5, userRequestDto.getTelecom());
			pstmt.setString(6, userRequestDto.getId());
			pstmt.execute();

			user = findUserById(userRequestDto.getId());
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return user;
	}

	public boolean deleteUserById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean isDeleted = false;

		String sql = "DELETE FROM users WHERE id = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			int rowsAffected = pstmt.executeUpdate();

			// 삭제가 성공적으로 이루어졌는지 확인
			if (rowsAffected > 0) {
				isDeleted = true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return isDeleted;
	}

	public UserResponseDto findUserByEmail(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserResponseDto user = null;
		String sql = "SELECT id,password,name,gender,email,phone,telecom,nickname,profile_img_url,is_staff FROM users WHERE email = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				String gender = rs.getString(4);
				String phone = rs.getString(6);
				String telecom = rs.getString(7);
				String nickname = rs.getString(8);
				String profile_img_url = rs.getString(9);
				boolean is_staff = rs.getBoolean(10);

				user = new UserResponseDto(id, password, name, gender, email, phone, telecom, nickname, profile_img_url, is_staff);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public UserResponseDto findUserByPhone(String phone) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserResponseDto user = null;
		String sql = "SELECT id,password,name,gender,email,phone,telecom,nickname,profile_img_url,is_staff FROM users WHERE phone = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				String gender = rs.getString(4);
				String email = rs.getString(5);
				String telecom = rs.getString(7);
				String nickname = rs.getString(8);
				String profile_img_url = rs.getString(9);
				boolean is_staff = rs.getBoolean(10);

				user = new UserResponseDto(id, password, name, gender, email, phone, telecom, nickname, profile_img_url, is_staff);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public UserResponseDto findUserByNickname(String nickname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserResponseDto user = null;
		String sql = "SELECT id,password,name,gender,email,phone,telecom,nickname,profile_img_url,is_staff FROM users WHERE nickname = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				String gender = rs.getString(4);
				String email = rs.getString(5);
				String phone = rs.getString(6);
				String telecom = rs.getString(7);
				String profile_img_url = rs.getString(9);
				boolean is_staff = rs.getBoolean(10);

				user = new UserResponseDto(id, password, name, gender, email, phone, telecom, nickname, profile_img_url, is_staff);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public String findNickNameById(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String nickname = null;

		String sql = "SELECT nickname FROM users WHERE id = ?";
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				nickname = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return nickname;
	}

	public String getProfileImageUrl(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String profileImageUrl = null;
		String sql = "SELECT profile_img_url FROM users WHERE id = ?";

		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				profileImageUrl = rs.getString("profile_img_url");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return profileImageUrl;
	}

	public List<UserResponseDto> searchUsersByNickname(String nickname) {
		List<UserResponseDto> userList = new ArrayList<>();
		String sql = "SELECT id,nickname FROM users WHERE nickname LIKE ?";

		try (Connection conn = DBManager.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, "%" + nickname + "%");
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					UserResponseDto user = new UserResponseDto(rs.getString(1),rs.getString(2));
					userList.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public String[] findUser(String id){
		String[] user = new String[2];
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;


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
