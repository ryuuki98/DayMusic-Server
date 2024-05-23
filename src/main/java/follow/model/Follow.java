package follow.model;

import java.sql.Timestamp;

public class Follow {
	private int followCode;
	private String followedId;
	private String followerId;
	private String nickname;
	
	public Follow(int followCode, String followedId, String followerId, String nickname) {
		super();
		this.followCode = followCode;
		this.followedId = followedId;
		this.followerId = followerId;
		this.nickname = nickname;
	}
	
	public int getFollowCode() {
		return followCode;
	}

	public void setFollowCode(int followCode) {
		this.followCode = followCode;
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
