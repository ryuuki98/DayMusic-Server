package board.module;

import java.sql.Timestamp;

public class BoardRequestDto {
	private int boardCode;
	private String id;
	private String nickname;
	private String contents;
	private String musicTrack;
	private String musicArtist;
	private String musicPreviewUrl;
	private String musicThumbnail;
	private String musicUrl;
	private int isPublic;
	private java.sql.Timestamp regDate;
	private java.sql.Timestamp modDate;
	
	public BoardRequestDto() {}
	
	public BoardRequestDto(int boardCode, String id, String nickname, String contents, String musicTrack, String musicArtist, String musicPreviewUrl, String musicThumbnail, String musicUrl, int isPublic, Timestamp regDate,
						   Timestamp modDate) {
		super();
		this.boardCode = boardCode;
		this.id = id;
		this.nickname = nickname;
		this.contents = contents;
		this.musicTrack = musicTrack;
		this.musicArtist = musicArtist;
		this.musicPreviewUrl = musicPreviewUrl;
		this.musicThumbnail = musicThumbnail;
		this.musicUrl = musicUrl;
		this.isPublic = isPublic;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public BoardRequestDto (String id, String nickname, String contents, String musicTrack, String musicArtist, String musicPreviewUrl, String musicThumbnail, String musicUrl, int isPublic) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.contents = contents;
		this.musicTrack = musicTrack;
		this.musicArtist = musicArtist;
		this.musicPreviewUrl = musicPreviewUrl;
		this.musicThumbnail = musicThumbnail;
		this.musicUrl = musicUrl;
		this.isPublic = isPublic;
	}
	
	// 글 작성 되는지 먼저 확인
	public BoardRequestDto (String id, String nickname, String contents, int isPublic) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.contents = contents;
		this.isPublic = isPublic;
	}
	
	public int getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() { return nickname; }
	public void setNickname(String nickname) { this.nickname = nickname; }
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getMusicTrack() {
		return musicTrack;
	}
	public void setMusicTrack(String musicTrack) {
		this.musicTrack = musicTrack;
	}
	public String getMusicArtist() { return musicArtist; }
	public void setMusicArtist(String musicArtist) { this.musicArtist = musicArtist; }
	public String getMusicPreviewUrl() { return musicPreviewUrl; }
	public void setMusicPreviewUrl(String musicPreviewUrl) { this.musicPreviewUrl = musicPreviewUrl; }
	public String getMusicThumbnail() { return musicThumbnail; }
	public void setMusicThumbnail(String musicThumbnail) { this.musicThumbnail = musicThumbnail; }
	public int isPublic() {
		return isPublic; 
	}

	public String getMusicUrl() {
		return musicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public void setPublic(int isPublic) {
		this.isPublic = isPublic;
	}
	public java.sql.Timestamp getRegDate() {
		return regDate;
	}
	public void setRegDate(java.sql.Timestamp regDate) {
		this.regDate = regDate;
	}
	public java.sql.Timestamp getModDate() {
		return modDate;
	}
	public void setModDate(java.sql.Timestamp modDate) {
		this.modDate = modDate;
	}
	
	
}
