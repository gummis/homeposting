package homeposting.app.ejb.impl;

import homeposting.app.domain.entities.AccountFlow;
import homeposting.app.domain.entities.Transaction;
import homeposting.app.ejb.TransactionsDao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public class TransactionsDaoImpl implements TransactionsDao {

	@PersistenceContext(unitName = "homeposting")
	private EntityManager em;
	
	public void createTransaction(Transaction transaction){
		em.merge(transaction.getCreator());
		em.merge(transaction.getTransactionSubkind());
		em.persist(transaction);
	}
	
	public long getCountAllTransactions(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Transaction.class)));
		long count =  em.createQuery(cq).getSingleResult();       
		return count;
	}
	
	public List<Transaction> getTransactions(int firstElementInd, int length, boolean orderAsc){
		Query q = em.createQuery("select t from Transaction t order by t.id " + (orderAsc ? "asc":"desc"),Transaction.class);
		q.setFirstResult(firstElementInd);
		q.setMaxResults(length);
		
		@SuppressWarnings("unchecked")
		List<Transaction> elements = q.getResultList();
		for(Transaction transaction :  elements){
			transaction.getFlows().size();
		}
		return elements;
	}

	public Transaction getTransactionById(Integer transactionId) {
		Transaction transaction = em.find(Transaction.class, transactionId);
		transaction.getFlows().size();
		return transaction;
	}

}
