package ca.vinote.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import ca.vinote.util.Categoria;

/**
 * Modelo de datos para el manejo de un vino
 * 
 * @author Alvaro Gomez
 * 
 */
@Entity
@Table(name="vino")
public class Vino implements Serializable {
	private static final long serialVersionUID = -7020071581694617767L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Integer id;
	
	@Column(nullable=false, length=20)
	private String nombre;
	@Column(nullable=false, length=20)
	private Categoria categoria;
	@Column(nullable=false, length=20)
	private Integer anio;
	@Column(nullable=false, length=100)
	private String imagen;
	@Column(nullable=false, length=200)
	private String descripcion;
	@Column(nullable=false, length=20)
	private Float precio;

	@ManyToOne
	@JoinColumn(name = "bodega_id", nullable = false)
	private Bodega bodega;

	public Vino() {

	}

	public Vino(String nombre, Categoria categoria, String descripcion,
			Integer anio, String imagen, Float precio, Bodega bodega) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.descripcion = descripcion;
		this.anio = anio;
		this.imagen = imagen;
		this.precio = precio;
		this.bodega = bodega;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Bodega getBodega() {
		return bodega;
	}

	public void setBodega(Bodega bodega) {
		this.bodega = bodega;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public boolean equals(Object obj) {
		boolean retorno = false;

		if (obj instanceof Vino) {
			if (this.id == ((Vino) obj).getId())
				retorno = true;
		}

		return retorno;
	}

	@Override
	public int hashCode() {
		return this.id + this.nombre.hashCode();
	}

}
