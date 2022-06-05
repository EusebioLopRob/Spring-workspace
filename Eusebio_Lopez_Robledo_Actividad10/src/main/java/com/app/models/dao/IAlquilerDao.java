package com.app.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.models.entity.Alquiler;
import com.app.models.entity.AlquilerPK;


public interface IAlquilerDao extends CrudRepository<Alquiler, AlquilerPK>{
	
	@Query("SELECT a FROM Alquiler a WHERE a.id.codCli = :codCli")
	public List<Alquiler> findByCliente(Long codCli);
	
}
