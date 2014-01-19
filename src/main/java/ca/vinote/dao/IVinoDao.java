package ca.vinote.dao;

import java.util.List;

import javax.persistence.PersistenceException;

import ca.vinote.model.Vino;

public interface IVinoDao extends IDao<Vino, Integer> {

	List<Vino> obtenerTopVentas() throws PersistenceException;

	List<Vino> obtenerNovedades() throws PersistenceException;

	List<Vino> buscar(String filtroBusqueda) throws PersistenceException;;

}
