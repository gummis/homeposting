package homeposting.app.web.transactions.manager;

import homeposting.app.common.domain.SubsystemWrapper;
import homeposting.app.ejb.TransactionsDao;
import homeposting.app.web.SessionBean;

import org.ocpsoft.logging.Logger;

public class EditGeneralTransactionManagedBean {

	private TransactionsDao transactionsDao;
	
	private final SubsystemWrapper subsystem;
	private TransactionBean transaction;
	private Integer transactionId;
	
	public EditGeneralTransactionManagedBean(TransactionsDao transactionsDao){
		if(transactionsDao == null){
			throw new NullPointerException("Dao Transakcji jest nullem");
		}
		this.transactionsDao = transactionsDao;
		subsystem = new SubsystemWrapper( SessionBean.getInstance().getSelectedSubsystem());
		Logger.getLogger(this.getClass()).info("Inicjalizacja bean obsługujacego edycję transakcji ogólnych this: "+this+" ");
	}
	
	public void reload(){
		Logger.getLogger(this.getClass()).info("reload "+this+" ");
		if(transactionId == null || transactionId == 0){
			//edycja nowej transakcji
			transaction = new TransactionBean(subsystem);
		}else{
			//edycja transakcji
			transaction = new TransactionBean(subsystem,transactionsDao.getTransactionById(transactionId));
		}
		Logger.getLogger(this.getClass()).info("Przeładowanie beana transakcji: "+transaction+" ");
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		Logger.getLogger(this.getClass()).info("setTransactionId "+this+" ");
		this.transactionId = transactionId;
	}

	public void addFlowAction(){
		Logger.getLogger(this.getClass()).info("addFlowAction "+this+" ");
		transaction.getFlows().add(new FlowBean(subsystem));
	}

	public void addTransactionAction(){
		Logger.getLogger(this.getClass()).info("addTransactionAction "+this+" ");
		transaction = new TransactionBean(subsystem);
	}

	public SubsystemWrapper getSubsystem() {
		return subsystem;
	}

	public TransactionBean getTransaction() {
		return transaction;
	}
	
	public void saveGeneralTransactionAction(){
		Logger.getLogger(this.getClass()).info("Zapisanie transakcji ogólnej "+this+" ");
		transactionsDao.createTransaction(transaction.getTransactionEntity());
		transaction = new TransactionBean(subsystem);
	}
	
}
