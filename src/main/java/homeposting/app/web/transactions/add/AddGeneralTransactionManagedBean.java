package homeposting.app.web.transactions.add;

import homeposting.app.common.domain.SubsystemWrapper;
import homeposting.app.common.security.Security;
import homeposting.app.ejb.TransactionsDao;
import homeposting.app.web.SessionBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.ocpsoft.logging.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ManagedBean(name = "addGeneralTransactionManagedBean", eager = true)
@ViewScoped
@URLMapping(id = "generalTransactionViewId", pattern = "/transactions/add/general", viewId = "/views/transactions/add/addGeneralTransaction.xhtml")
public class AddGeneralTransactionManagedBean {

	@EJB
	private TransactionsDao transactionsDao;
	
	private final SubsystemWrapper subsystem;
	private TransactionBean transaction;
	
	public AddGeneralTransactionManagedBean(){
		Security.logged();
		subsystem = new SubsystemWrapper( SessionBean.getInstance().getSelectedSubsystem());
		transaction = new TransactionBean(subsystem);
		Logger.getLogger(this.getClass()).info("Inicjalizacja bean obsługujacego tworzenie transakcji ogólnych");
	}

	public void addFlowAction(){
		transaction.getFlows().add(new FlowBean(subsystem));
	}

	public void addTransactionAction(){
		
		transaction = new TransactionBean(subsystem);
	}

	public SubsystemWrapper getSubsystem() {
		return subsystem;
	}

	public TransactionBean getTransaction() {
		return transaction;
	}
	
	public void saveGeneralTransactionAction(){
		Logger.getLogger(this.getClass()).info("Zapisanie transakcji ogólnej");
		transactionsDao.createTransaction(transaction.getTransactionEntity());
		transaction = new TransactionBean(subsystem);
	}
	
}
