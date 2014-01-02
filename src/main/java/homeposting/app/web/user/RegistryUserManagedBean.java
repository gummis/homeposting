package homeposting.app.web.user;

import homeposting.app.common.security.Security;
import homeposting.app.domain.entities.User;
import homeposting.app.domain.entities.UserKindEnum;
import homeposting.app.ejb.UsersDao;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.ocpsoft.logging.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

/**
 * Rejestrowanie użytkownika
 * @author sly
 */
@ManagedBean(name = "registryUserManagedBean", eager = true)
@ViewScoped
@URLMapping(id = "registryUserViewId", pattern = "/user/registry", viewId = "/views/user/registry_user.xhtml")
public class RegistryUserManagedBean implements Serializable {

	@EJB
	private UsersDao usersDao;

	private static final long serialVersionUID = 1L;

	private String login;
	private String password;
	private String rePassword;
	private String name;
	private String description;

	public RegistryUserManagedBean() {
		Security.unlogged();
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

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
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
		this.description = description;
	}

	public void registryUserAction() {
		if(!getPassword().equals(getRePassword())){
			throw new RuntimeException("Hasło nie jest zgodne z hasłem powtorzonym");
		}
		if(usersDao.isExistsUserByUsername(getLogin())){
			throw new RuntimeException("Użytkownik już istnieje");
		};
		
		User user = new User();
		user.setLogin(getLogin());
		user.setPassword(getPassword());
		user.setName(getName());
		user.setDescription(getDescription());
		user.setUserKind(UserKindEnum.USER);
		usersDao.createUser(user);
		Logger.getLogger(this.getClass()).info(
				"Zarejestrowanie uzytkownika: " + this);
	}

	@Override
	public String toString() {
		return "RegistryUserManagedBean [login=" + login + ", password="
				+ password + ", rePassword=" + rePassword + ", name=" + name
				+ ", description=" + description + "]";
	}

}
