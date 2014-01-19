package ca.vinote.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;


public abstract class BaseDao<T,K> implements IDao<T,K>{
	
	protected EntityManagerFactory entityManagerFactory;
	protected EntityManager entityManager;
	protected boolean autoCommit;
	private Class<T> clase;
		
	
	public BaseDao() {
		this(true);				//auto commit por defecto
	}
	
	@SuppressWarnings("unchecked")
	public BaseDao(boolean autoCommit) {
		this.autoCommit = autoCommit;
		this.clase = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];;
	}
	
	protected void init() {
		if (autoCommit)
			this.entityManager = entityManagerFactory.createEntityManager();
	}
	
	public boolean isAutoCommit() {
		return autoCommit;
	}

	public void setAutoCommit(boolean autoCommit) {
		this.autoCommit = autoCommit;
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void agregar(T obj) throws PersistenceException {
		entityManager.persist(obj);
		
	}

	@Override
	public void modificar(T obj) throws PersistenceException {
		entityManager.merge(obj);
		
	}

	@Override
	public void eliminar(K clave) throws PersistenceException {
		T elemento = this.obtener(clave);
		if(elemento != null){
			entityManager.remove(elemento);
		}
		
	}

	@Override
	public T obtener(K clave) throws PersistenceException {
		EntityManager entityManager = this.entityManager;
		if (entityManager == null)
			entityManager = entityManagerFactory.createEntityManager();
		
		return entityManager.find(clase, clave);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> obtenerTodos() throws PersistenceException {	
		EntityManager entityManager = this.entityManager;
		if (entityManager == null)
			entityManager = entityManagerFactory.createEntityManager();

		return (List<T>) entityManager.createQuery("from " + clase.getName()).getResultList();
	}
	
}
