package ca.vinote.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import ca.vinote.util.NivelUsuario;

@Entity
@JsonIgnoreProperties
public class Usuario implements Serializable {
	private static final long serialVersionUID = 820189638547799458L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Integer id;
	@Column(nullable = false, length = 20)
	private String nombre;
	@Column(nullable = false, length = 20)
	private NivelUsuario nivel;
	@Column(unique = true, nullable = false, length = 50)
	private String email;
	@Column(nullable = false, length = 20)
	private String password;

	// bi-directional many-to-one association to Vino
	@OneToMany(mappedBy = "usuario")
	private List<DatosUsuario> datosUsuario;

	public Usuario() {

	}

	public Usuario(String nombre, String email, String password) {
		this.nombre = nombre;
		this.email = email;
		this.password = password;
	}

	public Usuario(String nombre, String email, String password,
			List<DatosUsuario> datosUsuario) {
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.datosUsuario = datosUsuario;
	}

	public Usuario(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public NivelUsuario getNivel() {
		return nivel;
	}

	public void setNivel(NivelUsuario nivel) {
		this.nivel = nivel;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<DatosUsuario> getDatosUsuario() {
		return datosUsuario;
	}

	public void setDatosUsuario(List<DatosUsuario> datosUsuario) {
		this.datosUsuario = datosUsuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
