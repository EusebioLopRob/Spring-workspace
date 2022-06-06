package com.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.models.entity.Coche;


public interface ICocheDao extends CrudRepository<Coche, String>{

	@Query("SELECT c FROM Coche c WHERE c.alquilado = true")
	public List<Coche> findByCochesAlquilados();
}