package ca.vinote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import ca.vinote.model.Pedido;
import ca.vinote.model.Usuario;
import ca.vinote.util.Estado;

public class PedidoDao extends BaseDao<Pedido, Integer> implements IPedidoDao {

	public PedidoDao() {

	}

	public PedidoDao(boolean autoCommit) {
		super(autoCommit);
	}

	@SuppressWarnings("unchecked")
	public List<Pedido> obtener(Usuario u)
			throws PersistenceException {
		EntityManager entityManager = this.entityManager;
		if (entityManager == null)
			entityManager = entityManagerFactory.createEntityManager();

		return (List<Pedido>) entityManager.createQuery(
				"from Pedido p where p.usuario=" + u.getId()).getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> obtener(Usuario u, Estado estado) throws PersistenceException {
		EntityManager entityManager = this.entityManager;
		if (entityManager == null)
			entityManager = entityManagerFactory.createEntityManager();

		Query query = entityManager.createQuery(
				"from Pedido p where p.usuario=? and p.estado=?");
		
		query.setParameter(1, u);
		query.setParameter(2, estado);
				
		return (List<Pedido>) query.getResultList();
	}

}
