package user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBManager;
import util.PasswordCrypto;

public class UserDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

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
			System.out.println("비밀번호 불일치");
			user = null;
		}

		return user;
	}

	public UserResponseDto joinUser(UserRequestDto userRequestDto) {
		conn = DBManager.getConnection();
		UserResponseDto user = null;

		String sql = "INSERT INTO users (id,password,name,gender,email,phone,telecom,nickname,profile_img_url,is_staff) values (?,?,?,?,?,?,?,?,?,?)";

		try {
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
			System.out.println("가입 실패");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return user;
	}

	// id, password, name, gender, email, phone, telecom, nickname, profile_img_url,
	// is_staff
	public UserResponseDto findUserById(String id) {
		UserResponseDto user = null;

		conn = DBManager.getConnection();
		String sql = "SELECT id,password,name,gender,email,phone,telecom,nickname,profile_img_url,is_staff FROM users WHERE id = ?";

		try {
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
				String profile_img_url = rs.getString(9);
				boolean is_staff = rs.getBoolean(10);

				user = new UserResponseDto(id, password, name, gender, email, phone, telecom, nickname, profile_img_url,
						is_staff);
			}

		} catch (SQLException e) {
			System.out.println("findUserById 메소드 오류 ");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public boolean duplicateNickname(String id, String newNickname) {
		boolean isDuplicated = false;
		conn = DBManager.getConnection();
		String sql = "SELECT COUNT(*) FROM users WHERE id != ? AND nickname = ?";

		try {
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
			System.out.println("duplicateNickname method 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return isDuplicated;
	}

	public boolean duplicatePhone(String id, String newPhone) {
		boolean isDuplicated = false;
		conn = DBManager.getConnection();
		String sql = "SELECT COUNT(*) FROM users WHERE id != ? AND phone = ?";

		try {
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
			System.out.println("duplicatePhone method 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return isDuplicated;
	}

	public boolean duplicateEmail(String id, String newEmail) {
		boolean isDuplicated = false;
		conn = DBManager.getConnection();
		String sql = "SELECT COUNT(*) FROM users WHERE id != ? AND newEmail = ?";

		try {
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
			System.out.println("duplicateEmail method 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return isDuplicated;
	}

	public UserResponseDto updateNickname(String id, String newNickname) {
		UserResponseDto user = null;
		conn = DBManager.getConnection();
		String sql = "UPDATE users SET nickname = ? WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newNickname);
			pstmt.setString(2, id);
			pstmt.execute();
		} catch (SQLException e) {
			System.out.println("updateNickname 메소드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		user = findUserById(id);
		return user;
	}

	public UserResponseDto updatePassword(String id, String newPassword) {
		UserResponseDto user = null;
		conn = DBManager.getConnection();
		String sql = "UPDATE users SET password = ? WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, PasswordCrypto.encrypt(newPassword));
			pstmt.setString(2, id);

			pstmt.execute();

			user = findUserById(id);
		} catch (SQLException e) {
			System.out.println("updatePassword 메소드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return user;
	}

	public UserResponseDto updateProfileImage(String id, String profile_img_url) {
		conn = DBManager.getConnection();
		UserResponseDto user = null;

		System.out.println(id);
		System.out.println(profile_img_url);

		String sql = "UPDATE users SET profile_img_url = ? WHERE id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, profile_img_url);
			pstmt.setString(2, id);
			pstmt.execute();

			user = findUserById(id);
		} catch (SQLException e) {
			System.out.println("updateProfileImage 메소드 오류");
			e.printStackTrace();
		}

		return user;
	}

	public UserResponseDto UpdateInformation(UserRequestDto userRequestDto) {
		UserResponseDto user = null;

		String id = userRequestDto.getId();
		String newEmail = userRequestDto.getEmail();
		String newPhone = userRequestDto.getPhone();

		// unique key에 대한 중복 검증
		if (duplicateEmail(id, newEmail) || duplicatePhone(id, newPhone)) {
			return user;
		}

		conn = DBManager.getConnection();
		String sql = "UPDATE users SET name = ?, gender = ? ,email = ?,phone = ? ,telecom = ? WHERE id = ?";

		try {
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
			System.out.println("updateinformation 메소드 오류");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return user;
	}

	public boolean deleteUserById(String id) {
		boolean isDeleted = false;

		conn = DBManager.getConnection();

		String sql = "DELETE FROM users WHERE id = ? ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			int rowsAffected = pstmt.executeUpdate();

			// 삭제가 성공적으로 이루어졌는지 확인
			if (rowsAffected > 0) {
				isDeleted = true;
			}
		} catch (SQLException e) {
			System.out.println("deleteUserById 메소드 오류 ");
			e.printStackTrace();
		}

		return isDeleted;
	}

	public UserResponseDto findUserByEmail(String email) {
		UserResponseDto user = null;

		conn = DBManager.getConnection();
		String sql = "SELECT id,password,name,gender,email,phone,telecom,nickname,profile_img_url,is_staff FROM users WHERE email = ?";

		try {
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

				user = new UserResponseDto(id, password, name, gender, email, phone, telecom, nickname, profile_img_url,
						is_staff);
			}

		} catch (SQLException e) {
			System.out.println("findUserById 메소드 오류 ");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public UserResponseDto findUserByPhone(String phone) {
		UserResponseDto user = null;

		conn = DBManager.getConnection();
		String sql = "SELECT id,password,name,gender,email,phone,telecom,nickname,profile_img_url,is_staff FROM users WHERE phone = ?";

		try {
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

				user = new UserResponseDto(id, password, name, gender, email, phone, telecom, nickname, profile_img_url,
						is_staff);
			}

		} catch (SQLException e) {
			System.out.println("findUserById 메소드 오류 ");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public UserResponseDto findUserByNickname(String nickname) {
		UserResponseDto user = null;

		conn = DBManager.getConnection();
		String sql = "SELECT id,password,name,gender,email,phone,telecom,nickname,profile_img_url,is_staff FROM users WHERE nickname = ?";

		try {
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

				user = new UserResponseDto(id, password, name, gender, email, phone, telecom, nickname, profile_img_url,
						is_staff);
			}

		} catch (SQLException e) {
			System.out.println("findUserById 메소드 오류 ");
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return user;
	}

	public String findNickNameById(String id) {
		String nickname = null;
		conn = DBManager.getConnection();
		
		String sql = "SELECT nickname FROM users WHERE id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				id = rs.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nickname;
	}


}
