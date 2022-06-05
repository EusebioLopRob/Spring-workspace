package com.app.models.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Date;



/**
 * The persistent class for the ALQUILER database table.
 * 
 */
@Entity
@Table(name="ALQUILER")
public class Alquiler implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlquilerPK id;
	
	@Column(name="FECHAINICIO", insertable = false, updatable = false)
	private Date fechainicio;


	@Column(name="FECHAFIN")
	private Date fechafin;

	@Lob
	@Column(name="PRECIODIA")
	private String preciodia;

	@Column(name="valoracion")
	private int valoracion;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="COD_CLI", insertable = false, updatable = false)
	private Cliente cliente;

	//bi-directional many-to-one association to Jardineria
	@ManyToOne
	@JoinColumn(name="COD_PROD", insertable = false, updatable = false)
	private Jardineria jardineria;

	public Alquiler() {
	}

	public AlquilerPK getId() {
		return this.id;
	}

	public void setId(AlquilerPK id) {
		this.id = id;
	}

	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}
	
	public Date getFechafin() {
		return this.fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public String getPreciodia() {
		return this.preciodia;
	}

	public void setPreciodia(String preciodia) {
		this.preciodia = preciodia;
	}

	public int getValoracion() {
		return this.valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Jardineria getJardineria() {
		return this.jardineria;
	}

	public void setJardineria(Jardineria jardineria) {
		this.jardineria = jardineria;
	}

}