package com.app.models.service;

import java.util.List;

import com.app.models.entity.Alquiler;
import com.app.models.entity.AlquilerPK;

public interface IAlquilerService {

	public List<Alquiler> findAll();
	
	public Alquiler findOne(AlquilerPK id);
	
	public void delete(AlquilerPK id);
	
	public void save(Alquiler cliente);

	public List<Alquiler> findByCliente(Long codCli);
}
