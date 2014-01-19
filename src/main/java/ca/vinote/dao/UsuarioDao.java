package ca.vinote.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import ca.vinote.model.Usuario;

public class UsuarioDao extends BaseDao<Usuario, Integer> implements
		IUsuarioDao {

	public UsuarioDao() {

	}

	public UsuarioDao(boolean autoCommit) {
		super(autoCommit);
	}

	@Override
	public Usuario obtener(String email) throws PersistenceException {
		EntityManager entityManager = this.entityManager;
		if (entityManager == null)
			entityManager = entityManagerFactory.createEntityManager();
		Usuario u = null;
		try {
			u = (Usuario) entityManager.createQuery(
					"from Usuario u where u.email='" + email + "'")
					.getSingleResult();
		} catch (NoResultException e) {
			System.err.println("El usuario no está registrado");
			e.printStackTrace();
		}
		return u;
	}

}
