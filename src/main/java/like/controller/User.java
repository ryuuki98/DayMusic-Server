package like.controller;

public class User {
	private String id;
	private String password;
	private String name;
	private String gender;
	private String email;
	private String phone;
	private String telecom;
	private String nickname;
	private String profileImgUrl;
	
	public User(String id, String password, String name, String gender, String email, String phone, String telecom,
			String nickname, String profileImgUrl) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.telecom = telecom;
		this.nickname = nickname;
		this.profileImgUrl = profileImgUrl;
	}
	
	public User(String id, String nickname, String profileImgUrl) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.profileImgUrl = profileImgUrl;
	}
	
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getEmail() {
		return email;
	}
	public String getPhone() {
		return phone;
	}
	public String getTelecom() {
		return telecom;
	}
	public String getNickname() {
		return nickname;
	}
	public String getProfileImgUrl() {
		return profileImgUrl;
	}
	
	
}
