package ca.vinote.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;

import ca.vinote.dao.BaseDao;
import ca.vinote.dao.IVinoDao;
import ca.vinote.dao.TransactionProxy;
import ca.vinote.dao.TransactionProxyFactory;
import ca.vinote.exception.CustomException;
import ca.vinote.model.Vino;

public class VinoServiceImpl implements IVinoService {
	@Autowired
	private IVinoDao vinoDao;
	@Autowired
	private TransactionProxyFactory transactionProxyFactory;
	
	public VinoServiceImpl(){
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void agregar(Vino vino) throws CustomException {
		TransactionProxy proxy = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) vinoDao);
			vinoDao.agregar(vino);
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
	public void modificar(Vino vino) throws CustomException {
		TransactionProxy proxy = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) vinoDao);
			vinoDao.modificar(vino);
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
	public List<Vino> obtenerVinos() throws CustomException {
		TransactionProxy proxy = null;
		List<Vino> vinos = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) vinoDao);
			vinos = vinoDao.obtenerTodos();
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
		return vinos;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Vino> obtenerNovedades() throws CustomException {
		TransactionProxy proxy = null;
		List<Vino> vinos = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) vinoDao);
			vinos = vinoDao.obtenerNovedades();
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
		return vinos;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Vino> obtenerTopVentas() throws CustomException {
		TransactionProxy proxy = null;
		List<Vino> vinos = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) vinoDao);
			vinos = vinoDao.obtenerTopVentas();
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
		return vinos;
	}

	@Override
	public Vino obtener(Vino vino) throws CustomException {
		return this.obtener(vino.getId());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Vino obtener(Integer clave) throws CustomException {
		TransactionProxy proxy = null;
		Vino vino = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) vinoDao);
			vino = vinoDao.obtener(clave);
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
		return vino;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Vino> buscarVinos(String filtroBusqueda)
			throws CustomException {
		TransactionProxy proxy = null;
		List<Vino> vinos = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) vinoDao);
			vinos = vinoDao.buscar(filtroBusqueda);
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
		return vinos;
	}

	


}
