package com.ejemplos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.Libro;

public interface ILibroDao extends CrudRepository<Libro, String>{

}
