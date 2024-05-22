package like.controller;

public class Like {
	private int boardCode;
	private String id;
	private String nickname;
	private String profileImgUrl;
	
	public Like(int boardCode, String id) {
		super();
		this.boardCode = boardCode;
		this.id = id;
	}
	
	public Like(int boardCode, String id, String nickname, String profileImgUrl) {
		super();
		this.boardCode = boardCode;
		this.id = id;
		this.nickname = nickname;
		this.profileImgUrl = profileImgUrl;
	}

	public int getBoardCode() {
		return boardCode;
	}
	
	public int getboardCode() {
		return boardCode;
	}
	public String getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public String getProfileImgUrl() {
		return profileImgUrl;
	}
	
}


