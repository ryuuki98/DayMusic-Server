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
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			// SQL query to select posts by user ID
			String sql = "SELECT id, contents, music_track, music_album, music_artist, board_code, reg_date, mod_date, is_public, nickname FROM board WHERE id=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String contents = rs.getString("contents");
				String musicTrack = rs.getString("music_track");
				String musicAlbum = rs.getString("music_album");
				String musicArtist = rs.getString("music_artist");
				int boardCode = rs.getInt("board_code");
				java.sql.Timestamp regDate = rs.getTimestamp("reg_date");
				java.sql.Timestamp modDate = rs.getTimestamp("mod_date");
				int isPublic = rs.getInt("is_public");
				String nickname = rs.getString("nickname");

				BoardResponseDto board = new BoardResponseDto(boardCode, id, nickname, contents, musicTrack, musicAlbum, musicArtist, isPublic, regDate, modDate);
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
		
	}
	
	// 공개 게시글 리스트
	public List<BoardResponseDto> findBoardList() {
		List<BoardResponseDto> list = new ArrayList<BoardResponseDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			// SQL query to select only public posts
			String sql = "SELECT is_public, id, contents, music_track, music_album, music_artist, board_code, reg_date, mod_date, nickname FROM board WHERE is_public=0";

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// Only public posts are fetched by the query, so no need to filter here
				int isPublic = rs.getInt("is_public");
				String id = rs.getString("id");
				String contents = rs.getString("contents");
				String musicTrack = rs.getString("music_track");
				String musicAlbum = rs.getString("music_album");
				String musicArtist = rs.getString("music_artist");
				int boardCode = rs.getInt("board_code");
				java.sql.Timestamp regDate = rs.getTimestamp("reg_date");
				java.sql.Timestamp modDate = rs.getTimestamp("mod_date");
				String nickname = rs.getString("nickname");

				BoardResponseDto board = new BoardResponseDto(boardCode, id, nickname, contents, musicTrack, musicAlbum, musicArtist, isPublic, regDate, modDate);
				list.add(board);
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
		System.out.println("find code : "+ boardCode);
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT board_code, id, contents, music_track, music_album, music_artist, reg_date, mod_date, is_public, nickname FROM board WHERE board_code=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String id = rs.getString(2);
				String contents = rs.getString(3);
				String musicTrack = rs.getString(4);
				String musicAlbum = rs.getString(5);
				String musicArtist = rs.getString(6);
				java.sql.Timestamp reg_date = rs.getTimestamp(7);
				java.sql.Timestamp mod_date = rs.getTimestamp(8);
				int isPublic = rs.getInt(9);
				String nickname = rs.getString(10);
				
				board = new BoardResponseDto(boardCode, id, nickname, contents, musicTrack, musicAlbum, musicArtist, isPublic, reg_date, mod_date);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return board;
	}
	
	// 아이디로 개시물 찾기
	public BoardResponseDto findBoardById(String userId) {
		BoardResponseDto board = null;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "SELECT id, contents, music_track, music_album, music_artist, board_code, reg_date, mod_date, is_public, nickname FROM board WHERE id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String contents = rs.getString(2);
				String musicTrack = rs.getString(3);
				String musicAlbum = rs.getString(4);
				String musicArtist = rs.getString(5);
				int boardCode = rs.getInt(6);
				java.sql.Timestamp reg_date = rs.getTimestamp(7);
				java.sql.Timestamp mod_date = rs.getTimestamp(8);
				int isPublic = rs.getInt(9);
				String nickname = rs.getString(10);
				
				board = new BoardResponseDto(boardCode, userId, nickname, contents, musicTrack, musicAlbum, musicArtist, isPublic, reg_date, mod_date);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return board;
	}
	
	// 게시글 작성
	public BoardResponseDto createBoard(BoardRequestDto boardDto) {
		
		try {
			conn = DBManager.getConnection();
			String sql = "INSERT INTO board(id, contents, music_track, music_album, music_artist, is_public, nickname) VALUES(?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardDto.getId());
			pstmt.setString(2, boardDto.getContents());
			pstmt.setString(3, boardDto.getMusicTrack());
			pstmt.setString(4, boardDto.getMusicAlbum());
			pstmt.setString(5, boardDto.getMusicArtist());
			pstmt.setInt(6, boardDto.isPublic());
			pstmt.setString(7, boardDto.getNickname());
			
			pstmt.execute();
			
			return findBoardById(boardDto.getId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return null;
	}
	
	// 게시글 수정
	public BoardResponseDto updateBoardContents(BoardRequestDto boardDto) {
		BoardResponseDto board = null;
		
		if(findBoardById(boardDto.getId()) == null)
			return board;
		
		try {
			conn = DBManager.getConnection();
			
			String sql = "UPDATE board SET contents=?, music_track=?, music_album=?, music_artist=? WHERE id=? AND board_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getContents());
			pstmt.setString(2, boardDto.getMusicTrack());
			pstmt.setString(3, boardDto.getMusicAlbum());
			pstmt.setString(4, boardDto.getMusicArtist());
			pstmt.setString(5, boardDto.getId());
			pstmt.setInt(6, boardDto.getBoardCode());
			
			pstmt.execute();
			
			board = findBoardByCode(boardDto.getBoardCode());
			System.out.println("게시글 수정 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("게시글 수정 실패");
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return board;
	}
	
	// 게시글 삭제
	public boolean deleteBoard(String userId, int boardCode) {
		try {
			conn = DBManager.getConnection();
			String sql = "DELETE FROM board WHERE id=? AND board_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, boardCode);

			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
		return false;
	}
	
	
}
