package ca.vinote.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import ca.vinote.model.DatosUsuario;
import ca.vinote.model.Usuario;

public class DatosUsuarioDao extends BaseDao<DatosUsuario,Integer> implements IDatosUsuarioDao {

	public DatosUsuarioDao() {
	}
	
	public DatosUsuarioDao(boolean autoCommit){
		super(autoCommit);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DatosUsuario> obtener(Usuario u) throws PersistenceException {
		EntityManager entityManager = this.entityManager;
		if (entityManager == null)
			entityManager = entityManagerFactory.createEntityManager();

		return (List<DatosUsuario>) entityManager.createQuery(
				"from DatosUsuario d where d.usuario=" + u.getId()).getResultList();

	}
	
}
