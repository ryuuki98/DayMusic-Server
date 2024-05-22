package follow.model;

public class FollowRequestDto {
	private String id;
	private String followedId;
	private String followerId;
	
	public FollowRequestDto() {}
	
	public FollowRequestDto(String id, String followedId, String followerId) {
		super();
		this.id = id;
		this.followedId = followedId;
		this.followerId = followerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
