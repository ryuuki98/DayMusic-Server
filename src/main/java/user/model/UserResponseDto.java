package user.model;

public class UserResponseDto {
	private String id;
	private String password;
	private String name;
	private String gender;
	private String email;
	private String phone;
	private String telecom;
	private String nickname;
	private String profileImgUrl;
	private boolean is_staff;
	
	public UserResponseDto(String id, String password, String name, String gender, String email, String phone,
			String telecom, String nickname, String profileImgUrl, boolean is_staff) {
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
		this.is_staff = is_staff;
	}
	
	public UserResponseDto(String id, String nickname) {
		this.id = id;
		this.nickname = nickname;
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
	public boolean isIs_staff() {
		return is_staff;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}
	public void setIs_staff(boolean is_staff) {
		this.is_staff = is_staff;
	}

}
