package ca.vinote.service;

import java.util.List;

import ca.vinote.exception.CustomException;
import ca.vinote.model.DetallePedido;
import ca.vinote.model.Pedido;
import ca.vinote.model.Usuario;
import ca.vinote.util.Estado;

public interface IPedidoService {
	public void agregar(Pedido pedido) throws CustomException;

	public Pedido obtener(Pedido pedido) throws CustomException;

	public List<Pedido> obtenerPedidos() throws CustomException;

	public void modificar(Pedido p) throws CustomException;

	/**
	 * Devuelve los pedidos de un usuario específico
	 * 
	 * @param u
	 * @return
	 * @throws CustomException
	 */
	public List<Pedido> obtener(Usuario u) throws CustomException;

	/**
	 * Devuelve los pedidos de un usuario específico en un estado concreto
	 * 
	 * @param usuario
	 * @param estado
	 * @return
	 * @throws CustomException
	 */
	public List<Pedido> obtener(Usuario usuario, Estado estado)
			throws CustomException;

	/**
	 * Devuelve los detalles de un pedido
	 * 
	 * @param pedido
	 */
	public List<DetallePedido> obtenerDetallePedido(Pedido pedido)
			throws CustomException;
}
