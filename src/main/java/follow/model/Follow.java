package follow.model;

import java.sql.Timestamp;

public class Follow {
	private int followCode;
	private String followedId;
	private String followerId;
	private Timestamp regDate;
	private Timestamp modDate;
	
	public Follow(int followCode, String followedId, String followerId, Timestamp regDate, Timestamp modDate) {
		super();
		this.followCode = followCode;
		this.followedId = followedId;
		this.followerId = followerId;
		this.regDate = regDate;
		this.modDate = modDate;
	}

	public int getFollowCode() {
		return followCode;
	}

	public String getFollowedId() {
		return followedId;
	}

	public String getFollowerId() {
		return followerId;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}
}
