package com.ejemplos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

}
