package ca.vinote.dao;

import javax.persistence.PersistenceException;

import ca.vinote.model.Usuario;

public interface IUsuarioDao extends IDao<Usuario, Integer> {
	public Usuario obtener(String email) throws PersistenceException;
}
