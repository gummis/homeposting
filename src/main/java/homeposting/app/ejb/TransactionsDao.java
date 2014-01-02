package homeposting.app.ejb;

import java.util.List;

import homeposting.app.domain.entities.Transaction;

import javax.ejb.Local;

@Local
public interface TransactionsDao {
	public void createTransaction(Transaction transaction);
	public long getCountAllTransactions();
	public List<Transaction> getTransactions(int firstElementInd, int length, boolean orderAsc);
}
