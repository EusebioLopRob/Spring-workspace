package com.app.models;

import java.util.Objects;

public class Cliente {
	private String cod_cli;
	private String nombre;
	private String direccion;
	public Cliente() {
		super();
	}
	public Cliente(String cod_cli, String nombre, String direccion) {
		super();
		this.cod_cli = cod_cli;
		this.nombre = nombre;
		this.direccion = direccion;
	}
	public String getCod_cli() {
		return cod_cli;
	}
	public void setCod_cli(String cod_cli) {
		this.cod_cli = cod_cli;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cod_cli, direccion, nombre);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cod_cli, other.cod_cli) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(nombre, other.nombre);
	}
	@Override
	public String toString() {
		return "Cliente [cod_cli=" + cod_cli + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}
	
	
}
