package homeposting.app.ejb.impl;

import homeposting.app.common.data.Shortcut;
import homeposting.app.domain.entities.User;
import homeposting.app.ejb.UsersDao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class UsersDaoImpl implements UsersDao {

	@PersistenceContext(unitName = "homeposting")
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void createUser(User user) {
		em.persist(user);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public User getUserByLoginAndPassword(String login, String password) {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = qb.createQuery(User.class);
		Root<User> rootUser = query.from(User.class);
		query.where(qb.equal(rootUser.get("login"), login));
		User user = null;
		try{
			user = em.createQuery(query).getSingleResult();
			if(password.equals(user.getPassword())){
				return user;
			}else{
				return null;
			}
		}catch(NoResultException e){
		}
		return null;
	}

	public boolean isExistsUserByUsername(String username) {
		CriteriaBuilder qb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = qb.createQuery(User.class);
		Root<User> rootUser = query.from(User.class);
		query.where(qb.equal(rootUser.get("login"), username));
		User user = null;
		try{
			user = em.createQuery(query).getSingleResult();
		}catch(NoResultException e){
		}
		return user != null;
	}

	@Override
	public List<Shortcut> getUsersShortcutsBySubsystemId(Integer id) {
		
		List<Shortcut> set = new ArrayList<Shortcut>();
		
		Query query = em.createNativeQuery("select s.user_ref,u.login,u.name from subsystem_user s join usertab u on u.id = s.user_ref where s.subsystem_ref = :subsystem_id");
		query.setParameter("subsystem_id", id);
		List<Object[]> list = query.getResultList();
		for(Object[] arr : list){
			set.add(new Shortcut(Integer.parseInt(arr[0].toString()),arr[1].toString() + "(" + arr[2].toString() + ")"));
		}
/*
		String queryStr = "select new homeposting.app.common.data.Shortcut(user.id, user.login + '(' +user.name + ')') join from User as user where user.subsystem.id";
		Query query = em.createQuery(queryStr);
		@SuppressWarnings("unchecked")
		List<Shortcut> list = query.getResultList();
		
*/		
		return set;
	}

}
