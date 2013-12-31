package homeposting.app.web.transactions.add;

import homeposting.app.common.data.SubsystemWrapper;
import homeposting.app.domain.entities.TransactionKind;
import homeposting.app.domain.entities.TransactionSubkind;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

public class TransactionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private final SubsystemWrapper subsystem;

	private String description;
	private String identifier;
	private Date eventDate;
	private TransactionKind transactionKind;
	private Integer transactionKindId;
	private TransactionSubkind transactionSubkind;
	private Integer transactionSubkindId;
	private List<FlowBean> flows;
	private List<SelectItem> subkindItems;
	

	public TransactionBean(SubsystemWrapper subsystem){
		this.subsystem = subsystem;
		subkindItems = new ArrayList<SelectItem>();
		eventDate = new Date();
		flows = new ArrayList<FlowBean>();
		flows.add(new FlowBean(subsystem));
		setTransactionKindId(0);
	}
	
	public void setTransactionKindId(Integer id) {
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
		transactionSubkindId = id;
		transactionSubkind = transactionKind.getSubkinds().get(id);
	}

	public Integer getTransactionKindId() {
		return transactionKindId;
	}

	public Integer getTransactionSubkindId() {
		return transactionSubkindId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public List<SelectItem> getSubkindItems() {
		return subkindItems;
	}

	public String getIdentifier() {
		return identifier;
	}


	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}


	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public TransactionSubkind getTransactionSubkind() {
		return transactionSubkind;
	}
	public void setTransactionSubkind(TransactionSubkind transactionSubkind) {
		this.transactionSubkind = transactionSubkind;
	}
	public List<FlowBean> getFlows() {
		return flows;
	}
	public void setFlows(List<FlowBean> flows) {
		this.flows = flows;
	}
	
	
	
}
