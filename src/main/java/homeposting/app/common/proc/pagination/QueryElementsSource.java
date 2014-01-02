package homeposting.app.common.proc.pagination;

import homeposting.app.domain.entities.Transaction;

import java.util.List;

public interface QueryElementsSource {
	long getCount();
	List<Transaction> getElements(int firstElementInd, int length, boolean orderAsc);
}
