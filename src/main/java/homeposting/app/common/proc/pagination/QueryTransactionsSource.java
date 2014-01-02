package homeposting.app.common.proc.pagination;

import homeposting.app.domain.entities.Transaction;
import homeposting.app.ejb.TransactionsDao;

import java.util.List;

public class QueryTransactionsSource implements QueryElementsSource {

	private TransactionsDao transactionsDao;
	
	public QueryTransactionsSource(TransactionsDao transactionsDao){
		this.transactionsDao = transactionsDao;
	}
	
	public long getCount() {
		return transactionsDao.getCountAllTransactions();
	}

	public List<Transaction> getElements(int firstElementInd, int length, boolean orderAsc) {
		List<Transaction> transactions = transactionsDao.getTransactions(firstElementInd, length, orderAsc);
		return transactions;
	}

}
