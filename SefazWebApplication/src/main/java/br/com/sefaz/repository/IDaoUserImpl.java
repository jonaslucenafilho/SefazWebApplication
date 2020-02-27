package br.com.sefaz.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.sefaz.jpautil.JPAUtil;
import br.com.sefaz.model.User;

public class IDaoUserImpl implements IDaoUser {

	@Override
	public User consultUser(String email, String password) {

		User user = null;

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		user = (User) entityManager
				.createQuery("select u from User u where u.email = '" + email + "' and u.password = '" + password + "'")
				.getSingleResult();

		entityTransaction.commit();
		entityManager.close();

		return user;
	}

}
