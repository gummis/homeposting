package homeposting.app.domain.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "SUBSYSTEM")
public class Subsystem implements Serializable {

	private static final long serialVersionUID = 1L;

	public Subsystem(){
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotNull
	@Size(min = 3, max = 50)
	@Column(name = "NAME")
	private String name;

	@Size(max = 200)
	@Column(name = "DESCR")
	private String description;
	
	@JoinColumn(name = "USER_OWNER_REF", referencedColumnName = "ID")
	@ManyToOne()
	private User owner;
	
	@OneToMany(mappedBy="subsystem") 
	private List<Account> accounts;
	
	@OneToMany(mappedBy="subsystem") 
	private List<TransactionKind> transactionKinds;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		this.description = description != null && description.isEmpty() ? null : description;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void addAccount(Account account) {
		account.setSubsystem(this);
		accounts.add(account);
	}

	public void removeLastAccount() {
		accounts.remove(accounts.size()-1);
	}
	
	public void addTransactionKind(TransactionKind kind) {
		kind.setSubsystem(this);
		if(transactionKinds == null){
			transactionKinds = new ArrayList<TransactionKind>();
		}
		transactionKinds.add(kind);
	}

	public void removeLastTransactionKind() {
		transactionKinds.remove(transactionKinds.size()-1);
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public List<TransactionKind> getTransactionKinds() {
		return transactionKinds;
	}


	
}