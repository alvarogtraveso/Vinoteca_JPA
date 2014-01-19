package ca.vinote.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import ca.vinote.model.DetallePedido;
import ca.vinote.model.Pedido;

public class DetallePedidoDao extends BaseDao<DetallePedido,Integer> implements IDetallePedidoDao {
	
	public DetallePedidoDao() {
	
	}
	
	public DetallePedidoDao(boolean autoCommit) {
		super(autoCommit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DetallePedido> obtener(Pedido pedido)
			throws PersistenceException {
		EntityManager entityManager = this.entityManager;
		if (entityManager == null)
			entityManager = entityManagerFactory.createEntityManager();
		List<DetallePedido> dp = null;
		try {
			dp = (List<DetallePedido>) entityManager.createQuery(
					"from DetallePedido dp where dp.pedido='" + pedido.getId() + "'")
					.getResultList();
		} catch (NoResultException e) {
			System.err.println("Error al recoger los detalles del pedido.");
			e.printStackTrace();
		}
		return dp;	
	}

}
