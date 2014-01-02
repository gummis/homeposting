package homeposting.app.common.proc.pagination;

import homeposting.app.domain.entities.Transaction;

import java.util.List;

public class PaginationDataModel {
	private final PaginationContext context;
	public PaginationDataModel(PaginationContext context){
		this.context = context;
	}
	
	public List<Transaction> getTransactions(){
		return context.getElements();
	}
}
