package like.model;

public class LikeRequestDto {
	private int boardCode;
	private String id;
	private String nickname;
	private String profileImgUrl;
	
	public LikeRequestDto(int boardCode, String id) {
		super();
		this.boardCode = boardCode;
		this.id = id;
	}
	
	public LikeRequestDto(int boardCode, String id, String nickname, String profileImgUrl) {
		super();
		this.boardCode = boardCode;
		this.id = id;
		this.nickname = nickname;
		this.profileImgUrl = profileImgUrl;
	}

	public int getboardCode() {
		return boardCode;
	}

	public void setboardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
