package like.model;

public class UserRequestDto {
	private String id;
	private String password;
	private String name;
	private String gender;
	private String email;
	private String phone;
	private String telecom;
	private String nickname;
	private String profileImgUrl;
	
	public UserRequestDto(String id, String password, String name, String gender, String email, String phone,
			String telecom, String nickname, String profileImgUrl) {
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
	
	
	public UserRequestDto(String id, String nickname, String profileImgUrl) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.profileImgUrl = profileImgUrl;
	}



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTelecom() {
		return telecom;
	}
	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfileImgUrl() {
		return profileImgUrl;
	}
	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}
	
	
}	
