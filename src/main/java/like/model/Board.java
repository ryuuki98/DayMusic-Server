package like.model;

import java.security.Timestamp;

public class Board {
	private int boardCode;
	private String contents;
	private String id;
	private String musicCode;
	private boolean isPublic;
	private Timestamp redDate;
	private Timestamp modDate;
	
	public Board(int boardCode, String contents, String id, String musicCode, boolean isPublic, Timestamp redDate,
			Timestamp modDate) {
		super();
		this.boardCode = boardCode;
		this.contents = contents;
		this.id = id;
		this.musicCode = musicCode;
		this.isPublic = isPublic;
		this.redDate = redDate;
		this.modDate = modDate;
	}
	
	
	
	public Board(int boardCode, String contents, String id, String musicCode, boolean isPublic, Timestamp redDate) {
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
	public String getContents() {
		return contents;
	}
	public String getId() {
		return id;
	}
	public String getMusicCode() {
		return musicCode;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public Timestamp getRedDate() {
		return redDate;
	}
	public Timestamp getModDate() {
		return modDate;
	}
	
	
}
