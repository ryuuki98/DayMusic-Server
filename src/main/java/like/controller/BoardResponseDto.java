package like.controller;

import java.security.Timestamp;

public class BoardResponseDto {
	private int boardCode;
	private String contents;
	private String id;
	private String musicCode;
	private boolean isPublic;
	private Timestamp redDate;
	private Timestamp modDate;
	
	public BoardResponseDto(int boardCode, String contents, String id, String musicCode, boolean isPublic,
			Timestamp redDate) {
		super();
		this.boardCode = boardCode;
		this.contents = contents;
		this.id = id;
		this.musicCode = musicCode;
		this.isPublic = isPublic;
		this.redDate = redDate;
	}
	public int getBoardCode() {
		return boardCode;
	}
	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMusicCode() {
		return musicCode;
	}
	public void setMusicCode(String musicCode) {
		this.musicCode = musicCode;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public Timestamp getRedDate() {
		return redDate;
	}
	public void setRedDate(Timestamp redDate) {
		this.redDate = redDate;
	}
	public Timestamp getModDate() {
		return modDate;
	}
	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}
}
