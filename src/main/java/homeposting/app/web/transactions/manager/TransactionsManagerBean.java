package homeposting.app.web.transactions.manager;

import homeposting.app.common.data.util.LOG;
import homeposting.app.common.domain.TransactionWrapper;
import homeposting.app.common.security.Security;
import homeposting.app.ejb.TransactionsDao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ocpsoft.pretty.faces.annotation.URLMapping;

@Named
@ManagedBean(name = "transactionsManagerBean", eager = true)
@ViewScoped
@URLMapping(id = "transactionsManagerId", pattern = "/transactions/manager", viewId = "/views/transactions/manager/transactionsManager.xhtml")
public class TransactionsManagerBean {

	@EJB
	private TransactionsDao transactionsDao;

	@Inject
	private Conversation conversation;
	
	private boolean dirty;
	
	private static int counter = 0;
	public TransactionsManagerBean(){
		counter++;
		LOG.info("TransactionsManagerBean " + counter);
	}
	
	@PostConstruct
	public void init(){
		Security.logged();
		counter++;
		LOG.info("TransactionsManagerBean.init " + counter);
		editTransactionBean = new EditGeneralTransactionManagedBean(transactionsDao);
		dirty = true;
	}
	
	private EditGeneralTransactionManagedBean editTransactionBean;
	
	private TransactionsManagerModel model;
	private Integer currentId = null;
	private Integer currentRow = null;
	
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

	private static Long actions = 0L;
	public void setCurrentIdRow(Long currentId, Long currentRow) {
		actions++;
		LOG.info("setCurrentIdRow: " + this + " : " + currentId + " : " + currentRow);
		setCurrentId(currentId.intValue());
		setCurrentRow(currentRow.intValue());
	}
	
	
	public Long getActions() {
		LOG.info("getActions: " + this + " : " + actions);
		return actions;
	}

	public void actionCurrentId(Long currentId) {
		this.currentId = currentId.intValue();
		LOG.info("actionCurrentId: " + this + " : " + currentId);
		dirty = true;
	}
	
	public Integer getCurrentId() {
		LOG.info("getCurrentId: " + this + " : " + currentId);
		return currentId;
	}

	public void setCurrentId(Integer currentId) {
		this.currentId = currentId;
		LOG.info("setCurrentId: " + this + " : " + currentId);
		dirty = true;
	}

	public Integer getCurrentRow() {
		LOG.info("getCurrentRow " + this + " : " + currentRow);
		return currentRow;
	}

	public void setCurrentRow(Integer currentRow) {
		this.currentRow = currentRow;
		LOG.info("setCurrentRow " + this + " : " + currentRow);
		dirty = true;
	}

	public EditGeneralTransactionManagedBean getEditTransactionBean() {
		if(dirty){
			dirty= false;
			Integer curRow = getCurrentRow();
			editTransactionBean.setTransactionId((curRow == null || curRow == -1) ? null : getCurrentId());
			editTransactionBean.reload();
		}
		return editTransactionBean;
	}
	
	public void saveEditedTransactionAction(){
	}

	public int getCounter() {
		return counter;
	}
	
}
