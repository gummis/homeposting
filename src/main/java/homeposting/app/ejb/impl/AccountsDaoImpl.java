package homeposting.app.ejb.impl;

import homeposting.app.common.data.util.Pair;
import homeposting.app.domain.entities.Account;
import homeposting.app.ejb.AccountsDao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AccountsDaoImpl implements AccountsDao {

	@PersistenceContext(unitName = "homeposting")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Pair<Account,Integer>> getAccountsInfo(){
		Query q = em.createQuery("select new homeposting.app.common.data.util.Pair(a,a.initialState + sum(af.flow)) from AccountFlow af join af.account a group by a");
		return q.getResultList();
	}

}
