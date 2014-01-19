package ca.vinote.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import ca.vinote.model.Pedido;
import ca.vinote.model.Usuario;
import ca.vinote.util.Estado;

public interface IPedidoDao extends IDao<Pedido, Integer> {
	public List<Pedido> obtener(Usuario u) throws PersistenceException;

	public List<Pedido> obtener(Usuario usuario, Estado estado)throws PersistenceException;
}
