package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Marca;

public interface IMarcaService {

	public List<Marca> findAll();
	
	public Marca findOne(String id);
	
	public void delete(String id);
	
	public void save(Marca cliente);

}
