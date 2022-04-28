package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Producto;

public interface IProductoService {

	public List<Producto> findAll();
		
	public Producto findOne(Long id);
	
	public void delete(Long id);
	
	public void save(Producto producto);
	
	public List<Producto> findByNombreLikeIgnoreCase(String nombreProducto);

}
