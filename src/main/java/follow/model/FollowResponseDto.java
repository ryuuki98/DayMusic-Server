package follow.model;

public class FollowResponseDto {
	private String followedId;
	private String followerId;
	private String nickname;
	
	public FollowResponseDto(String followedId, String followerId) {
		super();
		this.followedId = followedId;
		this.followerId = followerId;
	}
	public FollowResponseDto(String nickname) {
		super();
		this.nickname = nickname;
	}

	public String getFollowedId() {
		return followedId;
	}

	public void setFollowedId(String followedId) {
		this.followedId = followedId;
	}

	public String getFollowerId() {
		return followerId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
