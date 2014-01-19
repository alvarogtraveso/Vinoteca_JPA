package ca.vinote.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;

import ca.vinote.dao.BaseDao;
import ca.vinote.dao.IDetallePedidoDao;
import ca.vinote.dao.IPedidoDao;
import ca.vinote.dao.TransactionProxy;
import ca.vinote.dao.TransactionProxyFactory;
import ca.vinote.exception.CustomException;
import ca.vinote.model.DetallePedido;
import ca.vinote.model.Pedido;
import ca.vinote.model.Usuario;
import ca.vinote.util.Estado;

public class PedidoServiceImpl implements IPedidoService {
	@Autowired
	private IPedidoDao pedidoDao;
	@Autowired
	private IDetallePedidoDao detallePedidoDao;
	@Autowired
	private TransactionProxyFactory transactionProxyFactory;

	public PedidoServiceImpl() {

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void agregar(Pedido pedido) throws CustomException {
		TransactionProxy proxy = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) pedidoDao);
			proxy.join((BaseDao) detallePedidoDao);
			pedidoDao.agregar(pedido);
			for (DetallePedido dp : pedido.getDetallesPedido()) {
				detallePedidoDao.agregar(dp);
			}

			proxy.commit();
		} catch (PersistenceException e) {
			if (proxy != null) {
				try {
					proxy.rollback();
				} catch (PersistenceException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Pedido obtener(Pedido pedido) throws CustomException {
		TransactionProxy proxy = null;
		Pedido p = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) pedidoDao);
			p = pedidoDao.obtener(pedido.getId());
			proxy.commit();
		} catch (PersistenceException e) {
			if (proxy != null) {
				try {
					proxy.rollback();
				} catch (PersistenceException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

		return p;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Pedido> obtenerPedidos() throws CustomException {
		TransactionProxy proxy = null;
		List<Pedido> pedidos = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) pedidoDao);
			pedidos = pedidoDao.obtenerTodos();
			proxy.commit();
		} catch (PersistenceException e) {
			if (proxy != null) {
				try {
					proxy.rollback();
				} catch (PersistenceException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

		return pedidos;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Pedido> obtener(Usuario u) throws CustomException {
		TransactionProxy proxy = null;
		List<Pedido> pedidos = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) pedidoDao);
			pedidos = pedidoDao.obtener(u);
			proxy.commit();
		} catch (PersistenceException e) {
			if (proxy != null) {
				try {
					proxy.rollback();
				} catch (PersistenceException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

		return pedidos;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void modificar(Pedido p) throws CustomException {
		TransactionProxy proxy = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) pedidoDao);
			pedidoDao.modificar(p);
			proxy.commit();
		} catch (PersistenceException e) {
			if (proxy != null) {
				try {
					proxy.rollback();
				} catch (PersistenceException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Pedido> obtener(Usuario usuario, Estado estado)
			throws CustomException {
		TransactionProxy proxy = null;
		List<Pedido> pedidos = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) pedidoDao);
			pedidos = pedidoDao.obtener(usuario, estado);
			proxy.commit();
		} catch (PersistenceException e) {
			if (proxy != null) {
				try {
					proxy.rollback();
				} catch (PersistenceException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

		return pedidos;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<DetallePedido> obtenerDetallePedido(Pedido pedido)
			throws CustomException {
		TransactionProxy proxy = null;
		List<DetallePedido> dp = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) detallePedidoDao);
			dp = detallePedidoDao.obtener(pedido);
			proxy.commit();
		} catch (PersistenceException e) {
			if (proxy != null) {
				try {
					proxy.rollback();
				} catch (PersistenceException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			throw new CustomException(e.getMessage());
		}

		return dp;
	}

}
