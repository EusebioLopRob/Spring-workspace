package com.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.List;


/**
 * The persistent class for the CLIENTE database table.
 * 
 */
@Entity
@Table(name="CLIENTE")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_CLI")
	private Long codCli;

	@NotEmpty
	@Column(name="DIRECCION")
	private String direccion;

	@NotEmpty
	@Column(name="NOMBRE")
	private String nombre;

	//bi-directional many-to-one association to Alquiler
	@OneToMany(mappedBy="cliente")
	private List<Alquiler> alquilers;

	public Cliente() {
	}

	public Long getCodCli() {
		return this.codCli;
	}

	public void setCodCli(Long codCli) {
		this.codCli = codCli;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
		alquiler.setCliente(this);

		return alquiler;
	}

	public Alquiler removeAlquiler(Alquiler alquiler) {
		getAlquilers().remove(alquiler);
		alquiler.setCliente(null);

		return alquiler;
	}

	@Override
	public String toString() {
		return "Cliente [codCli=" + codCli + ", direccion=" + direccion + ", nombre=" + nombre + "]";
	}

}