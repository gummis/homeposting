package homeposting.app.web.user.settings;

import homeposting.app.common.security.Security;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ManagedBean(name = "settingsUserManagedBean", eager = true)
@ViewScoped
@URLMapping(id = "settingsUserViewId", pattern = "/settings/user", viewId = "/views/user/settings/user.xhtml")
public class SettingsUserManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public SettingsUserManagedBean() {
		Security.logged();
	}/*
	
	public List<User> getUsersList(){
		return 
	}
	*/
	/*
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
*/

}
