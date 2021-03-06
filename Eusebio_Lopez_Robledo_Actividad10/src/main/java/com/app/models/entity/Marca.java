package com.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MARCA database table.
 * 
 */
@Entity
@Table(name="MARCA")
public class Marca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_MARCA")
	private String codMarca;

	@Column(name="NOMBRE_MARCA")
	private String nombreMarca;

	//bi-directional many-to-one association to Jardineria
	@OneToMany(mappedBy="marca")
	private List<Jardineria> jardinerias;

	public Marca() {
	}

	public String getCodMarca() {
		return this.codMarca;
	}

	public void setCodMarca(String codMarca) {
		this.codMarca = codMarca;
	}

	public String getNombreMarca() {
		return this.nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public List<Jardineria> getJardinerias() {
		return this.jardinerias;
	}

	public void setJardinerias(List<Jardineria> jardinerias) {
		this.jardinerias = jardinerias;
	}

	public Jardineria addJardineria(Jardineria jardineria) {
		getJardinerias().add(jardineria);
		jardineria.setMarca(this);

		return jardineria;
	}

	public Jardineria removeJardineria(Jardineria jardineria) {
		getJardinerias().remove(jardineria);
		jardineria.setMarca(null);

		return jardineria;
	}

}