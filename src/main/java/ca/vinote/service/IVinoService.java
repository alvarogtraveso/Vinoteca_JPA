package ca.vinote.service;

import java.util.List;

import ca.vinote.exception.CustomException;
import ca.vinote.model.Vino;

public interface IVinoService {
	public void agregar(Vino vino) throws CustomException;
	public Vino obtener(Vino vino) throws CustomException;
	public Vino obtener(Integer vinoId) throws CustomException;
	public List<Vino> obtenerNovedades() throws CustomException;
	public List<Vino> obtenerTopVentas() throws CustomException;
	public List<Vino> obtenerVinos() throws CustomException;
	public List<Vino> buscarVinos(String filtroBusqueda) throws CustomException;
	public void modificar(Vino vino) throws CustomException;
	
}
