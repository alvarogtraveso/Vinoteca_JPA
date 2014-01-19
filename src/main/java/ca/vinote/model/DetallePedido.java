package ca.vinote.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Modelo de datos del detalle de una compra. Contiene el 
 * @author Alvaro Gomez
 *
 */
@Entity
public class DetallePedido implements Serializable {
	private static final long serialVersionUID = 4606257838372917002L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "pedido_id", nullable = false)
	private Pedido pedido;
	@OneToOne
	@JoinColumn(name = "vino_id", nullable = false)
	private Vino vino;
	@Column(nullable = false, length = 20)
	private Integer cantidad;
	@Column(nullable = false, length = 20)
	private Float precio;
		
	
	public DetallePedido(Pedido pedido, Vino vino, Integer cantidad,
			Float precio) {		
		this.pedido = pedido;
		this.vino = vino;
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public DetallePedido() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Vino getVino() {
		return vino;
	}
	public void setVino(Vino vino) {
		this.vino = vino;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}
	
	
	

}
