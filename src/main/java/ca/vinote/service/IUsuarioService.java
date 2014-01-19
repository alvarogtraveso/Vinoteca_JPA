package ca.vinote.service;

import java.util.List;

import ca.vinote.exception.CustomException;
import ca.vinote.model.DatosUsuario;
import ca.vinote.model.Usuario;

public interface IUsuarioService {
	public void agregar(Usuario usuario) throws CustomException;
	public void modificarDatosUsuario(DatosUsuario datosUsuario) throws CustomException;
	public Usuario obtener(Usuario u) throws CustomException;
	public Usuario validar(Usuario u) throws CustomException;
	public List<Usuario> obtenerUsuarios() throws CustomException;
	public List<DatosUsuario> obtenerDatosUsuario(Usuario u) throws CustomException;
	public void modificar(Usuario u) throws CustomException;
}
