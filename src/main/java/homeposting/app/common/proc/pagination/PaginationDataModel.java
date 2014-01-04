package homeposting.app.common.proc.pagination;

import homeposting.app.common.domain.TransactionWrapper;
import homeposting.app.domain.entities.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PaginationDataModel {
	private final PaginationContext context;
	public PaginationDataModel(PaginationContext context){
		this.context = context;
	}
	
	List<TransactionWrapper> wrappers = null;
	
	public List<TransactionWrapper> getTransactions(){
		if(wrappers == null){
			List<Transaction> transactions = context.getElements();
			wrappers = new ArrayList<TransactionWrapper>(transactions.size());
			for(Transaction transaction : transactions){
				wrappers.add(new TransactionWrapper(transaction));
			}
		}
		return wrappers;
	}
	
	public String getColorClasses(){
		StringBuilder sb = new StringBuilder();
		for(TransactionWrapper wrapper : getTransactions()){
			if(sb.length() > 0){
				sb.append(",");
			}
			sb.append(wrapper.getType().getColorClass());
		}
		return sb.toString();
	}
	
}
