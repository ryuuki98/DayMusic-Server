package board.module;

import java.sql.Timestamp;

public class Board {
	
	private int boardCode;
	private String id;
	private String contents;
	private String musicCode;
	private boolean isPublic;
	private java.sql.Timestamp regDate;
	private java.sql.Timestamp modDate;
	
	public Board(int boardCode, String id, String contents, String musicCode, boolean isPublic, Timestamp regDate,
			Timestamp modDate) {
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

	public String getId() {
		return id;
	}

	public String getContents() {
		return contents;
	}

	public String getMusicCode() {
		return musicCode;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public java.sql.Timestamp getRegDate() {
		return regDate;
	}

	public java.sql.Timestamp getModDate() {
		return modDate;
	}
	
	
	
	
	
}
