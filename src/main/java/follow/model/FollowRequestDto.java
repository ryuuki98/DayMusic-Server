package follow.model;

public class FollowRequestDto {
	private String followedId;
	private String followerId;
	
	public FollowRequestDto() {}
	
	public FollowRequestDto(String followerId, String followedId) {
		super();
		this.followerId = followerId;
		this.followedId = followedId;
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
