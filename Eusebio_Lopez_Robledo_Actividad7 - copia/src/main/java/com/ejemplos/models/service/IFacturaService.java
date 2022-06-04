package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Factura;

public interface IFacturaService {

	public List<Factura> findAll();
		
	public Factura findOne(Long id);
	
	public void delete(Long id);
	
	public void save(Factura factura);

}
