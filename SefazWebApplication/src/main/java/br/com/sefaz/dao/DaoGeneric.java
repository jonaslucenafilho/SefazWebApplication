package br.com.sefaz.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.sefaz.jpautil.JPAUtil;

public class DaoGeneric<E> {

	public void save(E entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(entity);

		entityTransaction.commit();
		entityManager.close();
	}

	public E update(E entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		E updateEntity = entityManager.merge(entity);

		entityTransaction.commit();
		entityManager.close();

		return updateEntity;
	}
	
//	public void delete(E entity) {
//		EntityManager entityManager = JPAUtil.getEntityManager();
//		EntityTransaction entityTransaction = entityManager.getTransaction();
//		entityTransaction.begin();
//
//		entityManager.remove(entity);
//
//		entityTransaction.commit();
//		entityManager.close();
//	}
	
	public void delete(E entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		Object id = JPAUtil.getPrimaryKey(entity);
		entityManager.createQuery("delete from " + entity.getClass().getCanonicalName() + " where id = " + id).executeUpdate();

		entityTransaction.commit();
		entityManager.close();
	}
	
	public List<E> getListEntity(Class<E> entity) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		List<E> listEntity = entityManager.createQuery("from " + entity.getName()).getResultList();
		
		entityTransaction.commit();
		entityManager.close();
		
		return listEntity;
	}
}
