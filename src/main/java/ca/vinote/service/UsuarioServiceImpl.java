package ca.vinote.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;

import ca.vinote.dao.BaseDao;
import ca.vinote.dao.IDatosUsuarioDao;
import ca.vinote.dao.IUsuarioDao;
import ca.vinote.dao.TransactionProxy;
import ca.vinote.dao.TransactionProxyFactory;
import ca.vinote.exception.CustomException;
import ca.vinote.model.DatosUsuario;
import ca.vinote.model.Usuario;

public class UsuarioServiceImpl implements IUsuarioService {
	@Autowired
	private IUsuarioDao usuarioDao;
	@Autowired
	private IDatosUsuarioDao datosUsuarioDao;
	@Autowired
	private TransactionProxyFactory transactionProxyFactory;

	public UsuarioServiceImpl() {

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void agregar(Usuario usuario) throws CustomException {
		TransactionProxy proxy = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) usuarioDao);
			usuarioDao.agregar(usuario);
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
	public Usuario obtener(Usuario usuario) throws CustomException {
		TransactionProxy proxy = null;
		Usuario u = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) usuarioDao);
			u = usuarioDao.obtener(usuario.getEmail());
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
		
		return u;
	}

	@Override
	public List<Usuario> obtenerUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void modificarDatosUsuario(DatosUsuario datosUsuario) throws CustomException {
		TransactionProxy proxy = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) datosUsuarioDao);			
			datosUsuarioDao.modificar(datosUsuario);
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

	/**
	 * Comprueba si el usuario existe y si la contraseña es correcta
	 * @throws CustomException 
	 */
	@Override
	public Usuario validar(Usuario u) throws CustomException {
		Usuario usuario = this.obtener(u);
		if (usuario != null) {
			if (!usuario.getPassword().equals(u.getPassword()))
				usuario = null;
		}

		return usuario;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<DatosUsuario> obtenerDatosUsuario(Usuario u) throws CustomException {
		TransactionProxy proxy = null;
		List<DatosUsuario> du = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) datosUsuarioDao);
			du = datosUsuarioDao.obtener(u);
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
		
		return du;

	}

	@SuppressWarnings("rawtypes")
	@Override
	public void modificar(Usuario u) throws CustomException {
		TransactionProxy proxy = null;
		try {
			proxy = transactionProxyFactory.createTransactionProxy();
			proxy.join((BaseDao) usuarioDao);
			usuarioDao.modificar(u);
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

}
