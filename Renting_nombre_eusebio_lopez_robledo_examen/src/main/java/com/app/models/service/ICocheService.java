package com.app.models.service;

import java.util.List;
import com.app.models.entity.Coche;

public interface ICocheService {

	public List<Coche> findAll();
	
	public Coche findOne(String matricula);
	
	public void delete(String matricula);
	
	public void save(Coche coche);

	public List<Coche> findByCochesAlquilados();
}
