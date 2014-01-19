package ca.vinote.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Modelo de datos para Bodega
 * 
 * @author Alvaro Gomez
 * 
 */
@Entity
@Table(name = "bodega")
public class Bodega implements Serializable {
	private static final long serialVersionUID = -6350382948384631336L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Integer id;

	@Column(nullable = false, length = 20)
	private String nombre;

	@Column(nullable = false, length = 30)
	private String region;

	@OneToMany(mappedBy = "bodega")
	private List<Vino> vinos;

	public Bodega() {

	}

	public Bodega(String nombre, String region, List<Vino> vinos) {
		this.nombre = nombre;
		this.region = region;
		this.vinos = vinos;
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

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public List<Vino> getVinos() {
		return vinos;
	}

	public void setVinos(List<Vino> vinos) {
		this.vinos = vinos;
	}

}
