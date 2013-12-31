package homeposting.app.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegisterUser {

	public RegisterUser(){
	}
	
	@NotNull
	@Size(min = 3, max = 50)
	private String login;

	@NotNull
	@Size(min = 8, max = 50)
	private String password;

	@NotNull
	@Size(min = 8, max = 50)
	private String rePassword;
	
	@NotNull
	@Size(min = 3, max = 50)
	private String name;

	@Size(max = 200)
	private String description;
	

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
