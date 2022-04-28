package com.ejemplos.models.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ALQUILER database table.
 * 
 */
@Embeddable
public class AlquilerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_CLI", insertable=false, updatable=false)
	private Long codCli;

	@Column(name="COD_PROD", insertable=false, updatable=false)
	private String codProd;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="FECHAINICIO")
	private java.util.Date fechainicio;

	public AlquilerPK() {
	}
	public Long getCodCli() {
		return this.codCli;
	}
	public void setCodCli(Long codCli) {
		this.codCli = codCli;
	}
	public String getCodProd() {
		return this.codProd;
	}
	public void setCodProd(String codProd) {
		this.codProd = codProd;
	}
	public java.util.Date getFechainicio() {
		return this.fechainicio;
	}
	public void setFechainicio(java.util.Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AlquilerPK)) {
			return false;
		}
		AlquilerPK castOther = (AlquilerPK)other;
		return 
			(this.codCli == castOther.codCli)
			&& this.codProd.equals(castOther.codProd)
			&& this.fechainicio.equals(castOther.fechainicio);
	}
}