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

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}
}
