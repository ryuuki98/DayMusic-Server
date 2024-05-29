package board.module;

import java.sql.Timestamp;

public class BoardRequestDto {
	private int boardCode;
	private String id;
	private String nickname;
	private String contents;
	private String musicCode;
	private int isPublic;
	private java.sql.Timestamp regDate;
	private java.sql.Timestamp modDate;
	
	public BoardRequestDto() {}
	
	public BoardRequestDto(int boardCode, String nickname, String id, String contents, String musicCode, int isPublic,
			Timestamp regDate, Timestamp modDate) {
		super();
		this.boardCode = boardCode;
		this.id = id;
		this.nickname = nickname;
		this.contents = contents;
		this.musicCode = musicCode;
		this.isPublic = isPublic;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public BoardRequestDto (String id, String nickname, String contents, String musicCode, int isPublic) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.contents = contents;
		this.musicCode = musicCode;
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
	public String getMusicCode() {
		return musicCode;
	}
	public void setMusicCode(String musicCode) {
		this.musicCode = musicCode;
	}
	public int isPublic() {
		return isPublic; 
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
