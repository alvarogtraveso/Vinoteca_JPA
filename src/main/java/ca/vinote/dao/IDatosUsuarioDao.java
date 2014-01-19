package ca.vinote.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import ca.vinote.model.DatosUsuario;
import ca.vinote.model.Usuario;

public interface IDatosUsuarioDao extends IDao<DatosUsuario, Integer> {
	/**
	 * Devuelve la lista de Datos de usuario de un usuario dado.
	 * @param u
	 * @return
	 */
	public List<DatosUsuario> obtener(Usuario u) throws PersistenceException;

}
