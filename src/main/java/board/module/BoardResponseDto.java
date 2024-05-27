package board.module;

import java.sql.Timestamp;

public class BoardResponseDto {
	private int boardCode;
	private String id;
	private String contents;
	private String musicCode;
	private int isPublic;
	private java.sql.Timestamp regDate;
	private java.sql.Timestamp modDate;
	
	public BoardResponseDto() {}
	
	public BoardResponseDto(int boardCode, String id, String contents, String musicCode, int isPublic,
			Timestamp regDate, Timestamp modDate) {
		super();
		this.boardCode = boardCode;
		this.id = id;
		this.contents = contents;
		this.musicCode = musicCode;
		this.isPublic = isPublic;
		this.regDate = regDate;
		this.modDate = modDate;
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
