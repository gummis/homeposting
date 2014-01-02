package homeposting.app.ejb;

import homeposting.app.common.domain.Shortcut;
import homeposting.app.domain.entities.User;

import java.util.List;

import javax.ejb.Local;

@Local
public interface UsersDao {
	public void createUser(User user);
	public User getUserByLoginAndPassword(String login, String password);
	public boolean isExistsUserByUsername(String login);
	public List<Shortcut> getUsersShortcutsBySubsystemId(Integer id);
}
