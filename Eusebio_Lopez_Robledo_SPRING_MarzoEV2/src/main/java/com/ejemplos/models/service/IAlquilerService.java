package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Alquiler;
import com.ejemplos.models.entity.AlquilerPK;

public interface IAlquilerService {

	public List<Alquiler> findAll();
	
	public Alquiler findOne(AlquilerPK id);
	
	public void delete(AlquilerPK id);
	
	public void save(Alquiler cliente);

}
