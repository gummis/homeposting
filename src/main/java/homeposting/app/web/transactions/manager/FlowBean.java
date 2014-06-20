package homeposting.app.web.transactions.manager;

import homeposting.app.common.domain.SubsystemWrapper;
import homeposting.app.domain.entities.Account;
import homeposting.app.domain.entities.AccountFlow;

import java.io.Serializable;

public class FlowBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer accountFlowId; 
	private int cash;
	
	private Account account;
	private Integer accountId;
	private final SubsystemWrapper subsystem;
	
	public FlowBean(SubsystemWrapper subsystem) {
		this.subsystem = subsystem;
		accountFlowId = null;
		cash = 0;
		setAccountId(0);
	}
	
	public FlowBean(SubsystemWrapper subsystem, AccountFlow flow) {
		this.subsystem = subsystem;
		accountFlowId = flow.getId();
		cash = flow.getFlow();	
		setAccountId(flow.getAccount().getId());
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
	public AccountFlow getAccountFlow(){
		AccountFlow flow = new AccountFlow();
		flow.setId(accountFlowId);
		flow.setAccount(account);
		flow.setFlow(cash);
		return flow;
	}

	@Override
	public String toString() {
		return "FlowBean [accountFlowId=" + accountFlowId + ", cash=" + cash
				+ ", accountId=" + accountId + "]";
	}
}
