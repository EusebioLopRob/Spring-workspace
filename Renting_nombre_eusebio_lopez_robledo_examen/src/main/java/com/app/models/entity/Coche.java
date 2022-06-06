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
@Table(name="coches")
public class Coche implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name="matricula")
	private String matricula;

	@NotEmpty
	@Column(name="modelo")
	private String modelo;

	@NotEmpty
	@Column(name="marca")
	private String marca;
	
	@Column(name="alquilado")
	private boolean alquilado;

	public Coche() {
	}

	public Coche(String matricula, String modelo, String marca, boolean alquilado) {
		this.matricula = matricula;
		this.modelo = modelo;
		this.marca = marca;
		this.alquilado = alquilado;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public boolean isAlquilado() {
		return alquilado;
	}

	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Coche [matricula=" + matricula + ", modelo=" + modelo + ", marca=" + marca + ", alquilado=" + alquilado
				+ "]";
	}

	

}