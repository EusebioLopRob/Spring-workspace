package com.ejemplos.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the JARDINERIA database table.
 * 
 */
@Entity
@Table(name="JARDINERIA")
public class Jardineria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_PROD")
	private String codProd;

	@Column(name="CATEGORIA")
	private Long categoria;

	@Column(name="DESCRIPCION")
	private String descripcion;

	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to Alquiler
	@OneToMany(mappedBy="jardineria")
	private List<Alquiler> alquilers;

	//bi-directional many-to-one association to Marca
	@ManyToOne
	@JoinColumn(name="COD_MARCA")
	private Marca marca;

	public Jardineria() {
	}

	public String getCodProd() {
		return this.codProd;
	}

	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}

	public Long getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Alquiler> getAlquilers() {
		return this.alquilers;
	}

	public void setAlquilers(List<Alquiler> alquilers) {
		this.alquilers = alquilers;
	}

	public Alquiler addAlquiler(Alquiler alquiler) {
		getAlquilers().add(alquiler);
		alquiler.setJardineria(this);

		return alquiler;
	}

	public Alquiler removeAlquiler(Alquiler alquiler) {
		getAlquilers().remove(alquiler);
		alquiler.setJardineria(null);

		return alquiler;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

}