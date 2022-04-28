package com.ejemplos.models.dao;


import org.springframework.data.repository.CrudRepository;
import com.ejemplos.models.entity.Marca;


public interface IMarcaDao extends CrudRepository<Marca, String>{

	
}
