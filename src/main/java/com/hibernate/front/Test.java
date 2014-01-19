package com.hibernate.front;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence
				.createEntityManagerFactory("VinotecaJPA");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction();

//		// INSERT
//		Libro libro = new Libro();
//		libro.setAutor("Yo");
//		libro.setTitulo("Maldito");
//		libro.setPrecio(34.4f);
//
//		et.begin();
//		try {
//			em.persist(libro);
//			et.commit();
//		} catch (PersistenceException e) {
//			et.rollback();
//		} 
	}

}
