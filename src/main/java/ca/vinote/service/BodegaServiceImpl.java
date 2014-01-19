package ca.vinote.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;

import ca.vinote.dao.BaseDao;
import ca.vinote.dao.IBodegaDao;
import ca.vinote.dao.TransactionProxy;
import ca.vinote.dao.TransactionProxyFactory;
import ca.vinote.exception.CustomException;
import ca.vinote.model.Bodega;

public class BodegaServiceImpl implements IBodegaService {
	@Autowired
	private IBodegaDao bodegaDao;
	@Autowired
	private TransactionProxyFactory transactionProxyFactory;

	public BodegaServiceImpl() {

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void agregar(Bodega bodega) throws CustomException {
		TransactionProxy proxy = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) bodegaDao);
			bodegaDao.agregar(bodega);
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
	public List<Bodega> obtenerTodos() throws CustomException {
		TransactionProxy proxy = null;
		List<Bodega> bodegas = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) bodegaDao);
			bodegas = bodegaDao.obtenerTodos();
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
		
		return bodegas;
	}

}
