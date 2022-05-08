package com.ejemplos.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.Cliente;
import com.ejemplos.models.entity.Producto;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

	//@Query("SELECT c FROM Cliente c")
	@Query("SELECT c FROM Cliente c WHERE c.facturas is not empty")
	public List<Cliente> tienenFacturas();
	
}


//SELECT * FROM clientes c WHERE c.id IN (SELECT id_cliente FROM facturas);