package homeposting.app.web.transactions.manager;

import homeposting.app.common.data.util.DateUtil;
import homeposting.app.common.data.util.LOG;
import homeposting.app.common.domain.SubsystemWrapper;
import homeposting.app.domain.entities.AccountFlow;
import homeposting.app.domain.entities.Transaction;
import homeposting.app.domain.entities.TransactionKind;
import homeposting.app.domain.entities.TransactionSubkind;
import homeposting.app.web.SessionBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import org.ocpsoft.logging.Logger;

public class TransactionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private final SubsystemWrapper subsystem;

	private Integer transactionId;
	private Date postingDate;

	private String description;
	private Date eventDate;
	private String identifier;
	private TransactionKind transactionKind;
	private Integer transactionKindId;
	private TransactionSubkind transactionSubkind;
	private Integer transactionSubkindId;
	private List<FlowBean> flows;
	private List<SelectItem> subkindItems;
	

	public TransactionBean(SubsystemWrapper subsystem){
		this.subsystem = subsystem;
		transactionId = null;
		postingDate = null;
		subkindItems = new ArrayList<SelectItem>();
		description = "";
		eventDate = DateUtil.getNewDate();
		identifier = "";
		
		flows = new ArrayList<FlowBean>();
		flows.add(new FlowBean(subsystem));
		setTransactionKindId(0);
	}
	
	public TransactionBean(SubsystemWrapper subsystem, Transaction transaction){
		this.subsystem = subsystem;
		transactionId = transaction.getId();
		postingDate = transaction.getPostingDate();
		subkindItems = new ArrayList<SelectItem>();
		description = transaction.getDescription();
		eventDate = transaction.getEventDate();
		identifier = transaction.getIdentifier();

		flows = new ArrayList<FlowBean>();
		for(AccountFlow t: transaction.getFlows()){
			flows.add(new FlowBean(subsystem,t));
		}

		Integer skid = transaction.getTransactionSubkind().getId();
		Integer kid = transaction.getTransactionSubkind().getTransactionKind().getId();
		
		List<TransactionKind> tkList = subsystem.getSubsystem().getTransactionKinds();
		for(int kIndex = 0 ; kIndex < tkList.size() ; kIndex++){
			TransactionKind tk = tkList.get(kIndex);
			if(tk.getId().equals(kid)){
				List<TransactionSubkind> tskList = tk.getSubkinds();
				for(int skIndex = 0 ; skIndex < tskList.size() ; skIndex++){
					TransactionSubkind stk = tskList.get(skIndex);
					if(stk.getId().equals(skid)){
						transactionKind = tk;
						transactionKindId = kIndex;
						transactionSubkind = stk;
						transactionSubkindId = skIndex;						
						break;
					}
				}
				break;
			}
		}
		if(transactionKind == null || transactionSubkind == null ){
			throw new RuntimeException("Nie odnaleziono typu transakcji");
		}
		reloadSubkindLists();
	}
	
	public void setTransactionKindId(Integer id) {
		LOG.info(this, "setTransactionKindId="+id);
		transactionKindId = id;
		transactionKind = subsystem.getSubsystem().getTransactionKinds().get(id);
		reloadSubkindLists();
		setTransactionSubkindId(0);
	}
	
	private void reloadSubkindLists(){
		subkindItems.clear();
		int index = 0;
		for(TransactionSubkind sk : transactionKind.getSubkinds()){
			subkindItems.add(new SelectItem(index++,sk.getName()));
		}
	}

	public void setTransactionSubkindId(Integer id) {
		LOG.info(this, "setTransactionSubKindId="+id);
		transactionSubkindId = id;
		transactionSubkind = transactionKind.getSubkinds().get(id);
	}

	public Integer getTransactionKindId() {
		LOG.info(this, "getTransactionKindId="+transactionKindId);
		return transactionKindId;
	}

	public Integer getTransactionSubkindId() {
		LOG.info(this, "getTransactionSubKindId="+transactionSubkindId);
		return transactionSubkindId;
	}
	public String getDescription() {
		LOG.info(this, "getDescription="+description);
		return description;
	}
	public void setDescription(String description) {
		LOG.info(this, "setDescription="+description);
		this.description = description;
	}
	
	
	public List<SelectItem> getSubkindItems() {
		return subkindItems;
	}

	public String getIdentifier() {
		LOG.info(this, "getIdentifier="+identifier);
		return identifier;
	}


	public void setIdentifier(String identifier) {
		LOG.info(this, "setIdentifier="+identifier);
		this.identifier = identifier;
	}


	public Date getEventDate() {
		LOG.info(this, "getEventDate="+eventDate);
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		LOG.info(this, "setEventDate="+eventDate);
		this.eventDate = eventDate;
	}
	public TransactionSubkind getTransactionSubkind() {
		LOG.info(this, "getTransactionSubkind="+transactionSubkind);
		return transactionSubkind;
	}
	public void setTransactionSubkind(TransactionSubkind transactionSubkind) {
		LOG.info(this, "setTransactionSubkind="+transactionSubkind);
		this.transactionSubkind = transactionSubkind;
	}
	public List<FlowBean> getFlows() {
		LOG.info(this, "getFlows="+flows);
		return flows;
	}
	public void setFlows(List<FlowBean> flows) {
		LOG.info(this, "setFlows="+flows);
		this.flows = flows;
	}
	
	public Transaction getTransactionEntity(){
		Transaction transaction = new Transaction();
		transaction.setId(transactionId);
		transaction.setCreator(SessionBean.getInstance().getUser());
		transaction.setDescription(getDescription());
		transaction.setEventDate(getEventDate());
		transaction.setIdentifier(getIdentifier());
		transaction.setPostingDate(DateUtil.getNewDate());
		transaction.setTransactionSubkind(getTransactionSubkind());
		List<AccountFlow> flows = new ArrayList<AccountFlow>();
		for(FlowBean fb : getFlows()){
			AccountFlow af = fb.getAccountFlow();
			af.setTransaction(transaction);
			flows.add(af);
		}
		transaction.setFlows(flows);
		verify(transaction);
		return transaction;
	}
	
	public boolean isNew(){
		return transactionId == null || transactionId == 0;
	}
	
	public Integer getId(){
		return transactionId;
	}

	private void verify(Transaction transaction) {
		//zaden z przeplywów nie moze być zerowy oraz nie moze byc powtorzenia konta
		Set<Integer> ids = new HashSet<Integer>();
		
		for(AccountFlow af : transaction.getFlows()){
			if(af.getFlow() == 0 ){
				throw new RuntimeException("Jeden z przeływów jest zerowy");
			}
			if(ids.contains(af.getAccount().getId())){
				throw new RuntimeException("Nie mogą wystąpić więcej niż jeden przepływy dla jednego konta");
			}
			ids.add(af.getAccount().getId());
		}
	}

}
