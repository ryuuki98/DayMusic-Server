package board.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class BoardDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private BoardDao() {
		
	}
	
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	
	// 나의 게시물 찾기
	public List<BoardResponseDto> findBoardMyID(String userId) {
		List<BoardResponseDto> list = new ArrayList<BoardResponseDto>();
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT id, contents, music_code, board_code, reg_date, mod_date, is_public FROM board WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString(1);
				if(id.equals(userId)) {
					String contents = rs.getString(2);
					String musicCode = rs.getString(3);
					int boardCode = rs.getInt(4);
					java.sql.Timestamp reg_date = rs.getTimestamp(5);
					java.sql.Timestamp mod_date = rs.getTimestamp(6);
					int isPublic = rs.getInt(7);
					
					BoardResponseDto board = new BoardResponseDto(boardCode, id, contents, musicCode, isPublic, reg_date, mod_date);
					list.add(board);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
		
	}
	
	// 게시물 번호로 찾기
	public BoardResponseDto findBoardByCode(int boardCode) {
		BoardResponseDto board = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT board_code, id, contents, music_code, reg_date, mod_date, is_public FROM board WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString(2);
				String contents = rs.getString(3);
				String musicCode = rs.getString(4);
				java.sql.Timestamp reg_date = rs.getTimestamp(5);
				java.sql.Timestamp mod_date = rs.getTimestamp(6);
				int isPublic = rs.getInt(7);
				
				board = new BoardResponseDto(boardCode, id, contents, musicCode, isPublic, reg_date, mod_date);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return board;
	}
	
	// 
	public BoardResponseDto findBoardById(String userId) {
		BoardResponseDto board = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT id, contents, music_code, board_code, reg_date, mod_date, is_public FROM board WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String contents = rs.getString(2);
				String musicCode = rs.getString(3);
				int boardCode = rs.getInt(4);
				java.sql.Timestamp reg_date = rs.getTimestamp(5);
				java.sql.Timestamp mod_date = rs.getTimestamp(6);
				int isPublic = rs.getInt(7);
				
				board = new BoardResponseDto(boardCode, userId, contents, musicCode, isPublic, reg_date, mod_date);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}
	
	public BoardResponseDto createBoard(BoardRequestDto boardDto) {
		
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO board(id, contents, is_public) VALUES(?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardDto.getId());
			pstmt.setString(2, boardDto.getContents());
//			pstmt.setString(3, boardDto.getMusicCode());
			pstmt.setInt(3, boardDto.isPublic());
			
			pstmt.execute();
			
			return findBoardById(boardDto.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return null;
	}
	
	public boolean deleteBoard(BoardRequestDto boardDto) {
		
		if(findBoardByCode(boardDto.getBoardCode()) == null)
			return false;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "DELETE FROM board WHERE board_code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardDto.getBoardCode());
			
			pstmt.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return false;
	}
	
	
}
