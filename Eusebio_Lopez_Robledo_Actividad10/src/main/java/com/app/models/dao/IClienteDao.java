package com.app.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.models.entity.Cliente;


public interface IClienteDao extends CrudRepository<Cliente, Long>{

	@Query("SELECT c FROM Cliente c WHERE c.nombre = :nombre")
	public List<Cliente> findByNombre(String nombre);
}
