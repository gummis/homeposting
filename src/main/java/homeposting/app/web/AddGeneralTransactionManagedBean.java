package homeposting.app.web;

import homeposting.app.common.data.SubsystemWrapper;
import homeposting.app.web.transactions.add.FlowBean;
import homeposting.app.web.transactions.add.TransactionBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.ocpsoft.logging.Logger;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ManagedBean(name = "addGeneralTransactionManagedBean", eager = true)
@ViewScoped
@URLMapping(id = "generalTransactionViewId", pattern = "/transactions/add/general", viewId = "/views/transactions/add/addGeneralTransaction.xhtml")
public class AddGeneralTransactionManagedBean {

	private final SubsystemWrapper subsystem;
	private TransactionBean transaction;
	
	public AddGeneralTransactionManagedBean(){
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
	
}
