package com.ejemplos.models.dao;


import org.springframework.data.repository.CrudRepository;
import com.ejemplos.models.entity.Jardineria;


public interface IJardineriaDao extends CrudRepository<Jardineria, String>{

	
}