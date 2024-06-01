package board.module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import util.DBManager;

public class BoardDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private BoardDao() {
		conn = DBManager.getConnection();
	}

	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}

	private void closeResources() {
		DBManager.close(conn, pstmt, rs);
	}

	private void closeStatementAndResultSet() {
		DBManager.close(null, pstmt, rs);
	}

	// 나의 게시물 찾기
	public List<BoardResponseDto> findBoardMyID(String userId) {
		List<BoardResponseDto> list = new ArrayList<>();
		try {
			String sql = "SELECT id, contents, music_track, music_artist, music_PreviewUrl, music_Thumbnail, board_code, reg_date, mod_date, is_public, nickname FROM board WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String contents = rs.getString("contents");
				String musicTrack = rs.getString("music_track");
				String musicArtist = rs.getString("music_artist");
				String musicPreviewUrl = rs.getString("music_PreviewUrl");
				String musicThumbnail = rs.getString("music_Thumbnail");
				int boardCode = rs.getInt("board_code");
				java.sql.Timestamp regDate = rs.getTimestamp("reg_date");
				java.sql.Timestamp modDate = rs.getTimestamp("mod_date");
				int isPublic = rs.getInt("is_public");
				String nickname = rs.getString("nickname");

				BoardResponseDto board = new BoardResponseDto(boardCode, id, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail, isPublic, regDate, modDate);
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatementAndResultSet();
		}

		return list;
	}

	// 공개 게시글 리스트
	public List<BoardResponseDto> findBoardList() {
		List<BoardResponseDto> list = new ArrayList<>();
		try {
			String sql = "SELECT is_public, id, contents, music_track, music_artist, music_PreviewUrl, music_Thumbnail, board_code, reg_date, mod_date, nickname FROM board WHERE is_public=0";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int isPublic = rs.getInt("is_public");
				String id = rs.getString("id");
				String contents = rs.getString("contents");
				String musicTrack = rs.getString("music_track");
				String musicArtist = rs.getString("music_artist");
				String musicPreviewUrl = rs.getString("music_PreviewUrl");
				String musicThumbnail = rs.getString("music_Thumbnail");
				int boardCode = rs.getInt("board_code");
				java.sql.Timestamp regDate = rs.getTimestamp("reg_date");
				java.sql.Timestamp modDate = rs.getTimestamp("mod_date");
				String nickname = rs.getString("nickname");

				BoardResponseDto board = new BoardResponseDto(boardCode, id, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail, isPublic, regDate, modDate);
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatementAndResultSet();
		}

		return list;
	}

	// 게시물 번호로 찾기
	public BoardResponseDto findBoardByCode(int boardCode) {
		BoardResponseDto board = null;
		System.out.println("find code : " + boardCode);

		try {
			String sql = "SELECT board_code, id, contents, music_track, music_artist, music_PreviewUrl, music_Thumbnail, reg_date, mod_date, is_public, nickname FROM board WHERE board_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString(2);
				String contents = rs.getString(3);
				String musicTrack = rs.getString("music_track");
				String musicArtist = rs.getString("music_artist");
				String musicPreviewUrl = rs.getString("music_PreviewUrl");
				String musicThumbnail = rs.getString("music_Thumbnail");
				java.sql.Timestamp reg_date = rs.getTimestamp(7);
				java.sql.Timestamp mod_date = rs.getTimestamp(8);
				int isPublic = rs.getInt(9);
				String nickname = rs.getString(10);

				board = new BoardResponseDto(boardCode, id, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail, isPublic, reg_date, mod_date);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatementAndResultSet();
		}

		return board;
	}

	// 아이디로 개시물 찾기
	public BoardResponseDto findBoardById(String userId) {
		BoardResponseDto board = null;

		try {
			String sql = "SELECT id, contents, music_track, music_artist, music_PreviewUrl, music_Thumbnail, board_code, reg_date, mod_date, is_public, nickname FROM board WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String contents = rs.getString(2);
				String musicTrack = rs.getString("music_track");
				String musicArtist = rs.getString("music_artist");
				String musicPreviewUrl = rs.getString("music_PreviewUrl");
				String musicThumbnail = rs.getString("music_Thumbnail");
				int boardCode = rs.getInt(6);
				java.sql.Timestamp reg_date = rs.getTimestamp(7);
				java.sql.Timestamp mod_date = rs.getTimestamp(8);
				int isPublic = rs.getInt(9);
				String nickname = rs.getString(10);

				board = new BoardResponseDto(boardCode, userId, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail, isPublic, reg_date, mod_date);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatementAndResultSet();
		}

		return board;
	}

	// 게시글 작성
	public BoardResponseDto createBoard(BoardRequestDto boardDto) {

		try {
			String sql = "INSERT INTO board(id, contents, music_track, music_artist, music_PreviewUrl, music_Thumbnail, is_public, nickname) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardDto.getId());
			pstmt.setString(2, boardDto.getContents());
			pstmt.setString(3, boardDto.getMusicTrack());
			pstmt.setString(4, boardDto.getMusicArtist());
			pstmt.setString(5, boardDto.getMusicPreviewUrl());
			pstmt.setString(6, boardDto.getMusicThumbnail());
			pstmt.setInt(7, boardDto.isPublic());
			pstmt.setString(8, boardDto.getNickname());

			pstmt.execute();

			return findBoardById(boardDto.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStatementAndResultSet();
		}

		return null;
	}

	// 게시글 수정
	public BoardResponseDto updateBoardContents(BoardRequestDto boardDto) {
		BoardResponseDto board = null;

		if (findBoardById(boardDto.getId()) == null)
			return board;

		try {
			String sql = "UPDATE board SET contents=?, music_track=?, music_artist=?, music_PreviewUrl=?, music_Thumbnail=? WHERE id=? AND board_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getContents());
			pstmt.setString(2, boardDto.getMusicTrack());
			pstmt.setString(3, boardDto.getMusicArtist());
			pstmt.setString(4, boardDto.getMusicPreviewUrl());
			pstmt.setString(5, boardDto.getMusicThumbnail());
			pstmt.setString(6, boardDto.getId());
			pstmt.setInt(7, boardDto.getBoardCode());

			pstmt.execute();

			board = findBoardByCode(boardDto.getBoardCode());
			System.out.println("게시글 수정 완료");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("게시글 수정 실패");
		} finally {
			closeStatementAndResultSet();
		}

		return board;
	}

	// 게시글 삭제
	public boolean deleteBoard(String userId, int boardCode) {
		try {
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
			closeStatementAndResultSet();
		}

		return false;
	}
}
