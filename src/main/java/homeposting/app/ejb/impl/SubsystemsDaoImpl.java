package homeposting.app.ejb.impl;

import homeposting.app.common.domain.Shortcut;
import homeposting.app.domain.entities.Account;
import homeposting.app.domain.entities.Subsystem;
import homeposting.app.domain.entities.TransactionKind;
import homeposting.app.domain.entities.TransactionSubkind;
import homeposting.app.ejb.SubsystemsDao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SubsystemsDaoImpl implements SubsystemsDao {

	@PersistenceContext(unitName = "homeposting")
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void createSubsystem(Subsystem subsystem) {
		em.persist(subsystem);
	}

	public List<Shortcut> getAllSubsystemsShortuctsOfUser(Integer id) {

		String queryStr = "select new homeposting.app.common.domain.Shortcut(ss.id, ss.name) from Subsystem as ss";
		Query query = em.createQuery(queryStr);
		@SuppressWarnings("unchecked")
		List<Shortcut> list = query.getResultList();
		return list;
	}

	public Subsystem getSubsystemById(Integer id) {
		Subsystem s = em.find(Subsystem.class, id);
		if (s != null) {
			for (TransactionKind tk : s.getTransactionKinds()) {
				tk.getSubkinds().size();
			}
			s.getAccounts().size();
		}
		return s;
	}

	public void addUserToSubsystem(Integer idUser, Integer idSubsystem) {
		Query q = em
				.createNativeQuery("insert into subsystem_user(user_ref,subsystem_ref)values(:user,:subs)");
		q.setParameter("user", idUser);
		q.setParameter("subs", idSubsystem);
		q.executeUpdate();
	}

	public void addAccountToSubsystem(Subsystem subsystem, Account account) {
		em.merge(subsystem);
		subsystem.addAccount(account);
		try {
			em.persist(account);
			em.merge(subsystem);
		} catch (Exception e) {
			subsystem.removeLastAccount();
		}
	}

	public void addTransactionKindToSubsystem(Subsystem subsystem,
			TransactionKind transactionKind) {
		em.merge(subsystem);
		subsystem.addTransactionKind(transactionKind);
		try {
			em.persist(transactionKind);
			em.merge(subsystem);
		} catch (Exception e) {
			subsystem.removeLastTransactionKind();
		}
	}

	public void addTransactionSubkindToSubsystem(Subsystem subsystem,
			Integer transactionKindId, TransactionSubkind transactionSubkind) {
		TransactionKind findTk = null;
		for (TransactionKind tk : subsystem.getTransactionKinds()) {
			if (tk.getId().equals(transactionKindId))
				findTk = tk;
		}
		em.merge(findTk);
		findTk.addTransactionSubkind(transactionSubkind);
		try {
			em.persist(transactionSubkind);
			em.merge(findTk);
			em.merge(subsystem);
		} catch (Exception e) {
			subsystem.removeLastTransactionKind();
		}
	}

	public void removeTransactionSubkindFromSubsystem(
			Integer transactionSubkindId) {
		TransactionSubkind kind = em.find(TransactionSubkind.class,
				transactionSubkindId);
		em.remove(kind);
	}
}
