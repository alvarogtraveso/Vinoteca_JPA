package ca.vinote.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Modelo de datos para el manejo de la información de un usuario.
 * 
 * @author Alvaro Gomez
 * 
 */
@Entity
public class DatosUsuario implements Serializable {
	private static final long serialVersionUID = -5243419174727811177L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Integer id;
	@Column(nullable=false, length=20)
	private String nombre;
	@Column(nullable=false, length=40)
	private String apellidos;
	@Column(nullable=false, length=20)
	private String dni;
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;
	@Column(nullable=false, length=20)
	private String telefono;
	@Column(nullable=false, length=20)
	private String telefonoOpcional;
	@Column(nullable=false, length=200)
	private String direccion;
	@Column(nullable=false, length=5)
	private String codigoPostal;
	@Column(nullable=false, length=20)
	private String provincia;
	@Column(nullable=false, length=20)
	private String localidad;

	// bi-directional many-to-one association to Vino
	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;

	public DatosUsuario(String nombre, String apellidos, String dni,
			Date fechaNacimiento, String telefono, String telefonoOpcional,
			String direccion, String codigoPostal, String provincia,
			String localidad, Usuario usuario) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.telefonoOpcional = telefonoOpcional;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.localidad = localidad;
		this.usuario = usuario;
	}

	public DatosUsuario() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelefonoOpcional() {
		return telefonoOpcional;
	}

	public void setTelefonoOpcional(String telefonoOpcional) {
		this.telefonoOpcional = telefonoOpcional;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "DatosCompra [id=" + id + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", dni=" + dni + ", fechaNacimiento="
				+ fechaNacimiento + ", telefono=" + telefono
				+ ", telefonoOpcional=" + telefonoOpcional + ", direccion="
				+ direccion + ", codigoPostal=" + codigoPostal + ", provincia="
				+ provincia + ", localidad=" + localidad + ", usuario="
				+ usuario + "]";
	}

}
