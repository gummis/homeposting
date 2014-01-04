package homeposting.app.domain.entities;

import homeposting.app.common.jsf.CashConverter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ocpsoft.pretty.faces.config.convert.CaseConverter;

@Entity()
@Table(name = "TRANSACTIONTAB")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	public Transaction(){
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Size(max = 200)
	@Column(name = "DESCR")
	private String description;

	@Column(name = "EVENT_DATE")
	private Date eventDate;

	@NotNull
	@Column(name = "POSTING_DATE")
	private Date postingDate;

	@NotNull
	@Column(name = "IDENTIFIER")
	private String identifier;
	
	@OneToMany(mappedBy="transaction", cascade = {CascadeType.PERSIST})
	private List<AccountFlow> flows;
	
	@ManyToOne()
    @JoinColumn(name = "TRANS_SUB_KIND_REF")  
	private TransactionSubkind transactionSubkind;

	@ManyToOne()
    @JoinColumn(name = "USERTAB_CREATOR_REF")  
	private User creator;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description != null && description.isEmpty() ? null : description;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public List<AccountFlow> getFlows() {
		return flows;
	}

	public void setFlows(List<AccountFlow> flows) {
		this.flows = flows;
	}

	public TransactionSubkind getTransactionSubkind() {
		return transactionSubkind;
	}

	public void setTransactionSubkind(TransactionSubkind transactionSubkind) {
		this.transactionSubkind = transactionSubkind;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getFlowString(){
		StringBuilder sb = new StringBuilder();
		for(AccountFlow af: flows){
			if(sb.length() > 0)
				sb.append(";");
			sb.append(af.getAccount().getName());
			sb.append("=");
			sb.append(CashConverter.fromInteger(af.getFlow()));
		}
		return sb.toString();
	}
}