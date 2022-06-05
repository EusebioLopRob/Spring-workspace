package com.ejemplos.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="libros")
public class Libro {

	@Id
	@NotEmpty
	@Column(name="ISBN")
	private String isbn;
	
	@Column(name="TITULO")
	@NotEmpty
	private String titulo;
	
	@Column(name="AUTOR")
	@NotEmpty
	private String autor;
	
	@Column(name="PRESTADO")
	private boolean prestado;
	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public boolean isPrestado() {
		return prestado;
	}
	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}
	@Override
	public String toString() {
		return "Libro [isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", prestado=" + prestado + "]";
	}
}
