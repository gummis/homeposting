package homeposting.app.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "USERTAB")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	public User(){
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "LOGIN")
	private String login;

	@NotNull
	@Size(min = 8, max = 50)
	@Column(name = "PASSWORD")
	private String password;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "NAME")
	private String name;

	@Size(max = 200)
	@Column(name = "DESCR")
	private String description;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "KIND")
	private UserKindEnum userKind;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description != null && description.isEmpty() ? null : description;
	}

	public UserKindEnum getUserKind() {
		return userKind;
	}

	public void setUserKind(UserKindEnum userKind) {
		this.userKind = userKind;
	}
}