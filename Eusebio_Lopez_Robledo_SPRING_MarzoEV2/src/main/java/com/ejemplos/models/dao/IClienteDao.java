package com.ejemplos.models.dao;


import org.springframework.data.repository.CrudRepository;
import com.ejemplos.models.entity.Cliente;


public interface IClienteDao extends CrudRepository<Cliente, Long>{

	
}
