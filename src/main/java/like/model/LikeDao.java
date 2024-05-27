package like.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	
	public List<LikeResponseDto> printAllLikeList(int boardCode) {
	    List<LikeResponseDto> list = new ArrayList<LikeResponseDto>(); // 리스트 초기화
	    
	    try {
	        conn = DBManager.getConnection();
	        
	        String sql = "SELECT * FROM `like` WHERE board_code=?";
	        
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, boardCode);
	        
	        rs = pstmt.executeQuery();
	        while (rs.next()) {
	            String id = rs.getString(1);
	            UserDao finder = UserDao.getInstace();
	            String[] user = finder.findUser(id);
	            LikeResponseDto like = new LikeResponseDto(boardCode, id, user[0], user[1]);
	            list.add(like);                    
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        DBManager.close(conn, pstmt, rs);
	    }
	            
	    return list;
	}

	
	public LikeResponseDto findLike(String id,int boardCode){
		LikeResponseDto result = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT * FROM `like` WHERE id=? AND board_code=?";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, boardCode);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String responseId = rs.getString(1);
				int respnseBoardCode = rs.getInt(2);
				
				result = new LikeResponseDto(respnseBoardCode, responseId); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
				
		return result;
	}
	
	
	public String likeChange(String id, int boardCode) {
		String message = "";
		
		try {
			LikeResponseDto check = findLike(id, boardCode);
			
			if(check == null) {
				createLike(id, boardCode);
				message = "좋아요를 눌렀습니다.";
			}else {
				deleteLike(id, boardCode);
				message = "좋아요를 취소했습니다.";
			}
			System.out.println(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return message;
	}
	
	
	public LikeResponseDto createLike(String id, int boardCode) {
		LikeResponseDto result = null;
		
		
		try {
			conn = DBManager.getConnection();
			
			String Sql = "INSERT INTO `like` (id,board_code) VALUES (?,?)";
			pstmt = conn.prepareStatement(Sql);
			
			pstmt.setString(1, id);
			pstmt.setInt(2, boardCode);
			
			pstmt.execute();
			
			result = findLike(id, boardCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return result;
	}
	
	public boolean deleteLike(String id, int boardCode) {
		boolean result = false;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "DELETE FROM `like` WHERE board_code=? AND id=?";
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardCode);
			pstmt.setString(2, id);
			
			pstmt.execute();
			
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
		
		return result;
	}

	public Integer countLike(int board_code){
		int count = 0;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT COUNT(*) FROM `like` WHERE board_code=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1,board_code);

			pstmt.execute();

			if(rs.next()){
				count = rs.getInt(1);
			}

		} catch (Exception e){
			e.printStackTrace();
		}

		return  count;
	}
}
