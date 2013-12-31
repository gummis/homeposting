package homeposting.app.web.user.settings;

import homeposting.app.common.data.Shortcut;
import homeposting.app.domain.entities.Account;
import homeposting.app.domain.entities.Subsystem;
import homeposting.app.domain.entities.TransactionKind;
import homeposting.app.domain.entities.TransactionSubkind;
import homeposting.app.ejb.SubsystemsDao;
import homeposting.app.ejb.UsersDao;
import homeposting.app.web.SessionBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.ocpsoft.logging.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ManagedBean(name = "settingsSubsystemManagedBean", eager = true)
@ViewScoped
@URLMapping(id = "settingsSubsystemViewId", pattern = "/settings/subsystem", viewId = "/views/user/settings/subsystem.xhtml")
public class SettingsSubsystemManagedBean implements Serializable {

	@EJB
	private SubsystemsDao subsystemsDao;

	@EJB
	private UsersDao usersDao;

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String description;
	private String idAddedUser;
	
	private String accountName;
	private String accountDescription;
	private String accountInitialState;
	
	private String kindTransactionName;
	private String kindTransactionDescription;
	
	private String subkindTransactionKindId;
	private String subkindTransactionName;
	private String subkindTransactionDescription;

	public SettingsSubsystemManagedBean() {
		
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
	
	public String getIdAddedUser() {
		return idAddedUser;
	}

	public void setIdAddedUser(String id) {
		this.idAddedUser = id;
	}

	
	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountDescription() {
		return accountDescription;
	}

	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}

	public String getAccountInitialState() {
		return accountInitialState;
	}

	public void setAccountInitialState(String accountInitialState) {
		this.accountInitialState = accountInitialState;
	}
	
	

	public String getKindTransactionName() {
		return kindTransactionName;
	}

	public void setKindTransactionName(String kindTransactionName) {
		this.kindTransactionName = kindTransactionName;
	}

	public String getKindTransactionDescription() {
		return kindTransactionDescription;
	}

	public void setKindTransactionDescription(String kindTransactionDescription) {
		this.kindTransactionDescription = kindTransactionDescription;
	}
	
	public String getSubkindTransactionKindId() {
		return subkindTransactionKindId;
	}

	public void setSubkindTransactionKindId(String subkindTransactionKindId) {
		this.subkindTransactionKindId = subkindTransactionKindId;
	}

	public String getSubkindTransactionName() {
		return subkindTransactionName;
	}

	public void setSubkindTransactionName(String subkindTransactionName) {
		this.subkindTransactionName = subkindTransactionName;
	}

	public String getSubkindTransactionDescription() {
		return subkindTransactionDescription;
	}

	public void setSubkindTransactionDescription(
			String subkindTransactionDescription) {
		this.subkindTransactionDescription = subkindTransactionDescription;
	}
	
	public List<Account> getAccounts(){
		return SessionBean.getInstance().getSelectedSubsystem().getAccounts();
	}

	public List<Shortcut> getUsersShortcuts(){
		Subsystem subsystem = SessionBean.getInstance().getSelectedSubsystem();
		List<Shortcut> col = usersDao.getUsersShortcutsBySubsystemId(subsystem.getId());
		return col;
	}
	
	public void createSubsystemAction() {
		Subsystem subsystem = new Subsystem();
		subsystem.setName(getName());
		subsystem.setDescription(getDescription());
		subsystem.setOwner(SessionBean.getInstance().getUser());
		subsystemsDao.createSubsystem(subsystem);
		Logger.getLogger(this.getClass()).info(
				"Utworzenie podsystemu: " + this);
	}

	public void addUserToSubsystemAction(){
		Subsystem subsystem = SessionBean.getInstance().getSelectedSubsystem();
		if(subsystem != null){
			try{
				Integer id = Integer.parseInt(idAddedUser);
				subsystemsDao.addUserToSubsystem(id, subsystem.getId());
				Logger.getLogger(this.getClass()).info(
						"Dodanie użytkownika o id=" + idAddedUser + " do podsystemu '" + subsystem.getName() + "'");
				
			}catch(Exception e){
				
			}
		}
	}
	
	public void addAccountToSubsystemAction(){
		Subsystem subsystem = SessionBean.getInstance().getSelectedSubsystem();
		Account account = null;
		if(subsystem != null){
			try{
				account = new Account();
				account.setName(getAccountName());
				account.setDescription(getAccountDescription());
				Integer i = Integer.parseInt(getAccountInitialState());
				account.setInitialState(i);
			}catch(Exception e){
				return;
			}
			try{
				subsystemsDao.addAccountToSubsystem(subsystem,account);
			}catch(Exception e){
			}
		}
	}
	
	private List<Pair<String,List<String>>> tree = null;
	
	public List<Pair<String,List<String>>> getTransactionTypes(){
		if(tree == null){
			
			tree = new ArrayList<Pair<String,List<String>>>();
			for(TransactionKind tk : SessionBean.getInstance().getSelectedSubsystem().getTransactionKinds()){
				List<String> list = new ArrayList<String>();
				if(tk.getSubkinds() != null){
					for(TransactionSubkind stk : tk.getSubkinds()){
						list.add("" + stk.getId() + ". " + stk.getName());
					}
				}
				tree.add(new ImmutablePair<String,List<String>>("" + tk.getId() + ". " + tk.getName(), list));
			}
		}
		return tree;
	}
	
	
	public void addKindTransactionToSubsystemAction(){
		Subsystem subsystem = SessionBean.getInstance().getSelectedSubsystem();
		TransactionKind kind = null;
		if(subsystem != null){
			try{
				kind = new TransactionKind();
				kind.setName(getKindTransactionName());
				kind.setDescription(getKindTransactionDescription());
			}catch(Exception e){
				return;
			}
			try{
				subsystemsDao.addTransactionKindToSubsystem(subsystem,kind);
			}catch(Exception e){
			}
		}
	}
	public void addSubkindTransactionToSubsystemAction(){
		Subsystem subsystem = SessionBean.getInstance().getSelectedSubsystem();
		TransactionSubkind kind = null;
		Integer transactionKindId = null;
		if(subsystem != null){
			try{
				kind = new TransactionSubkind();
				kind.setName(getSubkindTransactionName());
				kind.setDescription(getSubkindTransactionDescription());
				transactionKindId = Integer.parseInt(getSubkindTransactionKindId());
			}catch(Exception e){
				return;
			}
			try{
				subsystemsDao.addTransactionSubkindToSubsystem(subsystem,transactionKindId, kind);
			}catch(Exception e){
			}
		}
	}
	@Override
	public String toString() {
		return "SettingsSubsystemManagedBean [name=" + name + ", description="
				+ description + "]";
	}

}
