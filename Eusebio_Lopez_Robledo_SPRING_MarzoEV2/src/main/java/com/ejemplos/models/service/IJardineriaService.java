package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Jardineria;

public interface IJardineriaService {

	public List<Jardineria> findAll();
	
	public Jardineria findOne(String id);
	
	public void delete(String id);
	
	public void save(Jardineria cliente);

}
