package ca.vinote.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ca.vinote.util.Estado;

/**
 * Modelo de datos que refleja una compra realizada por un usuario.
 * 
 * @author Alvaro Gomez
 * 
 */
@Entity
public class Pedido implements Serializable {
	private static final long serialVersionUID = -3139647675613873018L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Integer id;
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;
	@Column(nullable = false, length = 20)
	private Estado estado;
	/**
	 * Código bancareo que el usuario introduce para confirmar el pedido.
	 */
	@Column(length = 20)
	private String codigoConfirmacion;
	
	@Column(length = 200)
	private String motivoRechazo;

	@ManyToOne
	@JoinColumn(name = "usuario_id", nullable = false)
	private Usuario usuario;
	
	@OneToMany(mappedBy="pedido")
	private List<DetallePedido> detallesPedido;

	public Pedido() {

	}

	public Pedido(Date fecha, Estado estado, Usuario usuario,
			List<DetallePedido> detalles) {
		this.fecha = fecha;
		this.estado = estado;
		this.usuario = usuario;
		this.detallesPedido = detalles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCodigoConfirmacion() {
		return codigoConfirmacion;
	}

	public void setCodigoConfirmacion(String codigoConfirmacion) {
		this.codigoConfirmacion = codigoConfirmacion;
	}

	public List<DetallePedido> getDetallesPedido() {
		return detallesPedido;
	}

	public void setDetallesPedido(List<DetallePedido> detallesPedido) {
		this.detallesPedido = detallesPedido;
	}

}
