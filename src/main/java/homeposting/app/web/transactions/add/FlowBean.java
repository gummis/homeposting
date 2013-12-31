package homeposting.app.web.transactions.add;

import java.io.Serializable;

import homeposting.app.common.data.SubsystemWrapper;
import homeposting.app.domain.entities.Account;
import homeposting.app.domain.entities.Subsystem;

public class FlowBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private int cash;
	private Account account;
	private Integer accountId;
	private final SubsystemWrapper subsystem;
	
	public FlowBean(SubsystemWrapper subsystem) {
		this.subsystem = subsystem;
		cash = 0;
		setAccountId(0);
	}
	
	public void setAccountId(Integer id) {
		accountId = id;
		account = subsystem.getSubsystem().getAccounts().get(id);
	}

	public Integer getAccountId() {
		return accountId;
	}

	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
}
