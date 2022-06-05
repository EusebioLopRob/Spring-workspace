package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Libro;

public interface ILibroService {
	
	public List<Libro> findAll();
	
	public Libro findOne(String isbn);
	
	public void delete(String isbn);
	
	public void save(Libro libro);

}
