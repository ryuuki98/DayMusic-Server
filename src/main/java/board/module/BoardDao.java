package board.module;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class BoardDao {
	private static BoardDao instance = new BoardDao();

	public static BoardDao getInstance() {
		return instance;
	}

	// 나의 게시물 찾기
	public List<BoardResponseDto> findBoardMyID(String userId) {
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		List<BoardResponseDto> list = new ArrayList<>();
		try {
			String sql = "SELECT id, contents, music_track, music_artist, music_PreviewUrl, music_Thumbnail,music_url, board_code, reg_date, mod_date, is_public, nickname FROM board WHERE id=? ORDER BY reg_date DESC";
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
				String musicUrl = rs.getString("music_url");
				int boardCode = rs.getInt("board_code");
				Timestamp regDate = rs.getTimestamp("reg_date");
				Timestamp modDate = rs.getTimestamp("mod_date");
				int isPublic = rs.getInt("is_public");
				String nickname = rs.getString("nickname");

				BoardResponseDto board = new BoardResponseDto(boardCode, id, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail, musicUrl, isPublic, regDate, modDate);
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}

	// 나의 음악 게시물 찾기
	public List<BoardResponseDto> findMusicBoardMyID(String userId) {
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BoardResponseDto> list = new ArrayList<>();
		try {
			String sql = "SELECT id, contents, music_track, music_artist, music_PreviewUrl, music_Thumbnail, music_url, board_code, reg_date, mod_date, is_public, nickname FROM board WHERE id=? ORDER BY reg_date DESC";
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
				String musicUrl = rs.getString("music_url");
				Timestamp regDate = rs.getTimestamp("reg_date");
				Timestamp modDate = rs.getTimestamp("mod_date");
				int isPublic = rs.getInt("is_public");
				String nickname = rs.getString("nickname");

				if(!musicTrack.equals("")) {
					BoardResponseDto board = new BoardResponseDto(boardCode, id, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail, musicUrl, isPublic, regDate, modDate);
					list.add(board);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return list;
	}

	// 공개 게시글 리스트
	public List<BoardResponseDto> findBoardList() {
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BoardResponseDto> list = new ArrayList<>();
		try {
			String sql = "SELECT is_public, id, contents, music_track, music_artist, music_PreviewUrl, music_Thumbnail, music_url, board_code, reg_date, mod_date, nickname FROM board WHERE is_public=0 ORDER BY reg_date DESC";
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
				String musicUrl = rs.getString("music_url");
				int boardCode = rs.getInt("board_code");
				Timestamp regDate = rs.getTimestamp("reg_date");
				Timestamp modDate = rs.getTimestamp("mod_date");
				String nickname = rs.getString("nickname");

				BoardResponseDto board = new BoardResponseDto(boardCode, id, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail, musicUrl, isPublic, regDate, modDate);
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}


	// 팔로우 게시글 리스트
	public List<BoardResponseDto> followBoardList(String userId) {
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<BoardResponseDto> list = new ArrayList<>();
		try {
			String sql = "select * from board WHERE ID IN (SELECT follower_id FROM follow WHERE followed_id = ?) order by reg_date desc ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int isPublic = rs.getInt("is_public");
				String id = rs.getString("id");
				String contents = rs.getString("contents");
				String musicTrack = rs.getString("music_track");
				String musicArtist = rs.getString("music_artist");
				String musicPreviewUrl = rs.getString("music_PreviewUrl");
				String musicThumbnail = rs.getString("music_Thumbnail");
				String musicUrl = rs.getString("music_url");
				int boardCode = rs.getInt("board_code");
				Timestamp regDate = rs.getTimestamp("reg_date");
				Timestamp modDate = rs.getTimestamp("mod_date");
				String nickname = rs.getString("nickname");

				BoardResponseDto board = new BoardResponseDto(boardCode, id, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail, musicUrl, isPublic, regDate, modDate);
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return list;
	}

	// 게시물 번호로 찾기
	public BoardResponseDto findBoardByCode(int boardCode) {
		BoardResponseDto board = null;
		System.out.println("find code : " + boardCode);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT board_code, id, contents, music_track, music_artist, music_PreviewUrl, music_Thumbnail, music_url, reg_date, mod_date, is_public, nickname FROM board WHERE board_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardCode);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString("id");
				String contents = rs.getString("contents");
				String musicTrack = rs.getString("music_track");
				String musicArtist = rs.getString("music_artist");
				String musicPreviewUrl = rs.getString("music_PreviewUrl");
				String musicThumbnail = rs.getString("music_Thumbnail");
				String musicUrl = rs.getString("music_url");
				Timestamp reg_date = rs.getTimestamp("reg_date");
				Timestamp mod_date = rs.getTimestamp("mod_date");
				int isPublic = rs.getInt("is_public");
				String nickname = rs.getString("nickname");

				board = new BoardResponseDto(boardCode, id, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail, musicUrl, isPublic, reg_date, mod_date);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return board;
	}

	// 아이디로 개시물 찾기
	public BoardResponseDto findBoardById(String userId) {
		BoardResponseDto board = null;
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;


		try {
			String sql = "SELECT id, contents, music_track, music_artist, music_PreviewUrl, music_Thumbnail, music_url, board_code, reg_date, mod_date, is_public, nickname FROM board WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String contents = rs.getString(2);
				String musicTrack = rs.getString("music_track");
				String musicArtist = rs.getString("music_artist");
				String musicPreviewUrl = rs.getString("music_PreviewUrl");
				String musicThumbnail = rs.getString("music_Thumbnail");
				String musicUrl = rs.getString("music_url");
				int boardCode = rs.getInt(7);
				Timestamp reg_date = rs.getTimestamp(8);
				Timestamp mod_date = rs.getTimestamp(9);
				int isPublic = rs.getInt(10);
				String nickname = rs.getString(11);

				board = new BoardResponseDto(boardCode, userId, nickname, contents, musicTrack, musicArtist, musicPreviewUrl, musicThumbnail, musicUrl, isPublic, reg_date, mod_date);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}

		return board;
	}

	// 게시글 작성
	public BoardResponseDto createBoard(BoardRequestDto boardDto) {
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;
		int boardCode = 0;

		try {
			String sql = "INSERT INTO board(id, contents, music_track, music_artist, music_PreviewUrl, music_Thumbnail, music_url, is_public, nickname) VALUES(?, ?, ?, ?, ?, ?, ?, ?,?)";

			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, boardDto.getId());
			pstmt.setString(2, boardDto.getContents());
			pstmt.setString(3, boardDto.getMusicTrack());
			pstmt.setString(4, boardDto.getMusicArtist());
			pstmt.setString(5, boardDto.getMusicPreviewUrl());
			pstmt.setString(6, boardDto.getMusicThumbnail());
			pstmt.setString(7,boardDto.getMusicUrl());
			pstmt.setInt(8, boardDto.isPublic());
			pstmt.setString(9, boardDto.getNickname());

			pstmt.execute();

			ResultSet boardCodeRs = pstmt.getGeneratedKeys();
			if (boardCodeRs.next()) {boardCode = boardCodeRs.getInt(1);}

			return findBoardByCode(boardCode);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return null;
	}

	// 게시글 수정
	public BoardResponseDto updateBoardContents(BoardRequestDto boardDto) {
		BoardResponseDto board = null;
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;

		if (findBoardById(boardDto.getId()) == null)
			return board;

		try {
			String sql = "UPDATE board SET contents=?, music_track=?, music_artist=?, music_PreviewUrl=?, music_Thumbnail=?, music_url=? WHERE id=? AND board_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardDto.getContents());
			pstmt.setString(2, boardDto.getMusicTrack());
			pstmt.setString(3, boardDto.getMusicArtist());
			pstmt.setString(4, boardDto.getMusicPreviewUrl());
			pstmt.setString(5, boardDto.getMusicThumbnail());
			pstmt.setString(6, boardDto.getMusicUrl());
			pstmt.setString(7, boardDto.getId());
			pstmt.setInt(8, boardDto.getBoardCode());

			pstmt.execute();

			board = findBoardByCode(boardDto.getBoardCode());
			System.out.println("게시글 수정 완료");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("게시글 수정 실패");
		} finally {
			DBManager.close(conn, pstmt);
		}

		return board;
	}

	// 게시글 삭제
	public boolean deleteBoard(String userId, int boardCode) {
		Connection conn = DBManager.getConnection();
		PreparedStatement pstmt = null;

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
			DBManager.close(conn, pstmt);
		}

		return false;
	}
}