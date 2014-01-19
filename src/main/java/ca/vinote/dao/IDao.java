package ca.vinote.dao;

import java.util.List;

import javax.persistence.PersistenceException;

public interface IDao<T, K> {
	public void agregar(T obj) throws PersistenceException;
	public void modificar(T obj) throws PersistenceException;
	public void eliminar(K clave) throws PersistenceException;
	public T obtener(K clave) throws PersistenceException;
	public List<T> obtenerTodos() throws PersistenceException;
}
