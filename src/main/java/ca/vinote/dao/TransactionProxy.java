package ca.vinote.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;

public class TransactionProxy {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	public void init() {
		try {
			entityManager = entityManagerFactory.createEntityManager();
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
		} catch (PersistenceException e) {
			System.err.println("Error abriendo la conexión");
			e.printStackTrace();
		}		
	}
	
	@SuppressWarnings("rawtypes")
	public void join(BaseDao dao) {
		dao.setEntityManager(entityManager);
	}
	
	public void commit() {
		entityTransaction.commit();
	}

	public void rollback() {
		entityTransaction.rollback();
	}

	public void cerrarConexion() {
		entityManager.close();
	}

}
