package com.ejemplos.models.dao;


import org.springframework.data.repository.CrudRepository;
import com.ejemplos.models.entity.Alquiler;
import com.ejemplos.models.entity.AlquilerPK;


public interface IAlquilerDao extends CrudRepository<Alquiler, AlquilerPK>{

	
}
