package follow.model;

public class FollowResponseDto {
	private String followedId;
	private String followerId;
	
	public FollowResponseDto(String followedId, String followerId) {
		super();
		this.followedId = followedId;
		this.followerId = followerId;
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
}
