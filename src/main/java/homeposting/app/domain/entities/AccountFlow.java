package homeposting.app.domain.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity()
@Table(name = "ACCOUNT_FLOW")
public class AccountFlow implements Serializable {

	private static final long serialVersionUID = 1L;

	public AccountFlow(){
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@NotNull
	@Column(name = "FLOW")
	private Integer flow;

	@ManyToOne
    @JoinColumn(name = "ACCOUNT_REF")  
	private Account account;
	
	@ManyToOne
    @JoinColumn(name = "TRANSACTIONTAB_REF")  
	private Transaction transaction;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFlow() {
		return flow;
	}

	public void setFlow(Integer flow) {
		this.flow = flow;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}