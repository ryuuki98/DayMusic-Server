package board.module;

import java.sql.Timestamp;

public class Board {
	
	private int boardCode;
	private String id;
	private String nickname;
	private String contents;
	private String musicTrack;
	private String musicAlbum;
	private String musicArtist;
	private boolean isPublic;
	private java.sql.Timestamp regDate;
	private java.sql.Timestamp modDate;
	
	public Board(int boardCode, String id, String nickname, String contents, String musicTrack, String musicAlbum, String musicArtist, boolean isPublic, Timestamp regDate,
			Timestamp modDate) {
		super();
		this.boardCode = boardCode;
		this.id = id;
		this.nickname = nickname;
		this.contents = contents;
		this.musicTrack = musicTrack;
		this.musicAlbum = musicAlbum;
		this.musicArtist = musicArtist;
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

	public String getNickname() { return nickname; }

	public String getContents() {
		return contents;
	}

	public String getMusicCode() {
		return musicTrack;
	}

	public String getMusicAlbum() { return musicAlbum; }

	public String getMusicArtist() { return musicArtist; }

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
