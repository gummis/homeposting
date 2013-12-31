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
import javax.validation.constraints.Size;

@Entity()
@Table(name = "TRANS_SUB_KIND")
public class TransactionSubkind implements Serializable {

	private static final long serialVersionUID = 1L;

	public TransactionSubkind(){
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
	
	@ManyToOne
    @JoinColumn(name = "KIND_REF")  
	private TransactionKind transactionKind;

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

	public TransactionKind getTransactionKind() {
		return transactionKind;
	}

	public void setTransactionKind(TransactionKind transactionKind) {
		this.transactionKind = transactionKind;
	}

}