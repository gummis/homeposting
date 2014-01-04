package homeposting.app.web.transactions.manager;

import homeposting.app.common.domain.TransactionWrapper;
import homeposting.app.common.security.Security;
import homeposting.app.ejb.TransactionsDao;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

@ManagedBean(name = "transactionsManagerBean", eager = true)
@ViewScoped
@URLMapping(id = "transactionsManagerId", pattern = "/transactions/manager", viewId = "/views/transactions/manager/transactionsManager.xhtml")
public class TransactionsManagerBean {

	
	public TransactionsManagerBean(){
		Security.logged();
	}
	
	@EJB
	TransactionsDao transactionsDao;
	
	private TransactionsManagerModel model;

	public List<TransactionWrapper> getTransactions(){
		if(model == null){
			model = new TransactionsManagerModel(transactionsDao);
		}
		return model.getTransactions();
	}
	
	public String getColorClasses(){
		getTransactions();
		return model.getColorClasses();
	}
	
}
