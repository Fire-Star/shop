package exam.domain;

public class UserBase {
	private String username;
	private String password;
	private String vertifyCode;
	private String mainActive;
	private String role;
	
	public String getMainActive() {
		return mainActive;
	}
	public void setMainActive(String mainActive) {
		this.mainActive = mainActive;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserBase(String username, String password, String vertifyCode, String mainActive, String role) {
		super();
		this.username = username;
		this.password = password;
		this.vertifyCode = vertifyCode;
		this.mainActive = mainActive;
		this.role = role;
	}
	public String getVertifyCode() {
		return vertifyCode;
	}
	public void setVertifyCode(String vertifyCode) {
		this.vertifyCode = vertifyCode;
	}
	public UserBase() {
		super();
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserBase [username=" + username + ", password=" + password + ", vertifyCode=" + vertifyCode
				+ ", mainActive=" + mainActive + ", role=" + role + "]";
	}
}
