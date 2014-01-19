package ca.vinote.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import ca.vinote.model.DetallePedido;
import ca.vinote.model.Pedido;

public interface IDetallePedidoDao extends IDao<DetallePedido, Integer> {

	List<DetallePedido> obtener(Pedido pedido) throws PersistenceException;

}
