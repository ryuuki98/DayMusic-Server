package follow.model;

public class FollowResponseDto {
	private String followerId;
	private String followedId;
	private String nickname;
	
	public FollowResponseDto(String followerId, String followedId) {
		super();
		this.followerId = followerId;
		this.followedId = followedId;
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
