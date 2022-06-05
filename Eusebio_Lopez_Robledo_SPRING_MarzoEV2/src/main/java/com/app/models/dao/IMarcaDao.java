package com.app.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.app.models.entity.Marca;


public interface IMarcaDao extends CrudRepository<Marca, String>{

	
}
