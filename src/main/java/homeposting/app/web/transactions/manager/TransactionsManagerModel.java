package homeposting.app.web.transactions.manager;

import homeposting.app.common.proc.pagination.PaginationContext;
import homeposting.app.common.proc.pagination.PaginationDataModel;
import homeposting.app.common.proc.pagination.QueryTransactionsSource;
import homeposting.app.ejb.TransactionsDao;

public class TransactionsManagerModel extends PaginationDataModel{
	public TransactionsManagerModel(TransactionsDao transactionsDao){
		super(new PaginationContext(new QueryTransactionsSource(transactionsDao)));
	}
	
	
	/*
	private PaginationContext context;
	
	public PaginedBufDataModel(PaginationContext pc){
		super(metdata);
		context = pc;
		reset();
	}
	


	public void reset() {
		context.reset();
		clear();
		addDataElements(context.getElements());
	}
	public boolean first() {
		if(context.first()){
			clear();
			addDataElements(context.getElements());
			return true;
		}
		return false;
	}
	public boolean last() {
		if(context.last()){
			clear();
			addDataElements(context.getElements());
			return true;
		}
		return false;
	}
	public boolean next() {
		if(context.next()){
			clear();
			addDataElements(context.getElements());
			return true;
		}
		return false;
	}
	public boolean prev() {
		if(context.previous()){
			clear();
			addDataElements(context.getElements());
			return true;
		}
		return false;
	}
	public boolean setPage(int ind) {
		if(context.setPage(ind)){
			clear();
			addDataElements(context.getElements());
			return true;
		}
		return false;
	}

	public PaginationContext getPaginationContext() {
		return context;
	}
*/
}
