package ca.vinote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import ca.vinote.model.Vino;
import ca.vinote.util.Categoria;

public class VinoDao extends BaseDao<Vino, Integer> implements IVinoDao {

	public VinoDao() {

	}

	public VinoDao(boolean autoCommit) {
		super(autoCommit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vino> obtenerTopVentas() throws PersistenceException {
		EntityManager entityManager = this.entityManager;
		if (entityManager == null)
			entityManager = entityManagerFactory.createEntityManager();
		
		Query query = entityManager.createQuery("SELECT d.vino FROM DetallePedido d " +
				"GROUP BY d.vino.id ORDER BY SUM(d.cantidad) DESC");
		
		return (List<Vino>) query.setMaxResults(10).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vino> obtenerNovedades() throws PersistenceException {
		EntityManager entityManager = this.entityManager;
		if (entityManager == null)
			entityManager = entityManagerFactory.createEntityManager();

		return (List<Vino>) entityManager
				.createQuery("from Vino v order by v.id desc")
				.setMaxResults(10).getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Vino> buscar(String filtroBusqueda) throws PersistenceException {
		EntityManager entityManager = this.entityManager;
		if (entityManager == null)
			entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager
				.createQuery("select v from Vino v join v.bodega b"
						+ " where v.nombre like ? or v.categoria=? or b.nombre like ?");

		query.setParameter(1, "%" + filtroBusqueda + "%");
		try{
			query.setParameter(2, Categoria.valueOf(filtroBusqueda.toUpperCase()));
		} catch (IllegalArgumentException e){
			System.err.println("No se puede parsear como categoría de vino");
			query.setParameter(2, null);
		}
		
		query.setParameter(3, "%" + filtroBusqueda + "%");

		return (List<Vino>) query.getResultList();

	}

}
