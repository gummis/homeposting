package homeposting.app.web;

import java.util.List;

import homeposting.app.common.data.Shortcut;
import homeposting.app.domain.LoginUserBean;
import homeposting.app.domain.entities.Subsystem;
import homeposting.app.domain.entities.User;
import homeposting.app.ejb.SubsystemsDao;
import homeposting.app.ejb.UsersDao;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

@ManagedBean(name= "sessionBean")
@SessionScoped
public class SessionBean {

	@EJB
	UsersDao usersDao;

	@EJB
	SubsystemsDao subsystemsDao;
	
	private final ContextSession context;

	private User user;
	private LoginUserBean loginUserBean;
	
	private Subsystem selectedSubsystem;
	private List<SelectItem> subsystemsItems;
	
	public SessionBean(){
		context = new ContextSession();
		loginUserBean = new LoginUserBean();
	}
	
	public ContextSession getContext(){
		return context;
	}
	
	public User getUser(){
		return user;
	}
	
	public LoginUserBean getLoginUserBean(){
		return loginUserBean;
	}
	
	public Subsystem getSelectedSubsystem(){
		return selectedSubsystem;
	}

	public Integer getSelectedSubsystemId(){
		return selectedSubsystem != null ? selectedSubsystem.getId(): null;
	}

	public void setSelectedSubsystemId(Integer id){
		if(id == null){
			subsystemsItems = null;
			selectedSubsystem = null;
		}else{
			selectSubsystem(id);
		}
	}

	public List<SelectItem> getSubsystemsItems() {
		return subsystemsItems;
	}

	public String loginAction(){
		user = usersDao.getUserByLoginAndPassword(loginUserBean.getLogin(), loginUserBean.getPassword());
		if(user != null ){
			reloadSubsystemsShortcuts();
			loginUserBean = null;
		}
		return "pretty:start";
	}

	private void reloadSubsystemsShortcuts() {
		List<Shortcut> shortcuts = subsystemsDao.getAllSubsystemsShortuctsOfUser(user.getId());
		if(!shortcuts.isEmpty()){
			selectSubsystem(shortcuts.get(0).getId());
			subsystemsItems = Shortcut.toSelectItemList(shortcuts);
		}else{
			subsystemsItems = null;
			selectedSubsystem = null;
		}
			
	}

	private void selectSubsystem(Integer id) {
		if(selectedSubsystem == null || !selectedSubsystem.getId().equals(id) ){
			selectedSubsystem = subsystemsDao.getSubsystemById(id);
		}
	}

	public String logoutAction(){
		loginUserBean = new LoginUserBean();
		subsystemsItems = null;
		selectedSubsystem = null;
		user = null;
		return "pretty:start";
	}

	public static SessionBean getInstance() {
		return (SessionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionBean");
	};
}
