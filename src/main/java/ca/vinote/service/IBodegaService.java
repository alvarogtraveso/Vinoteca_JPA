package ca.vinote.service;

import java.util.List;

import ca.vinote.exception.CustomException;
import ca.vinote.model.Bodega;


public interface IBodegaService {
	public void agregar(Bodega bodega) throws CustomException;
	public List<Bodega> obtenerTodos() throws CustomException;
}
