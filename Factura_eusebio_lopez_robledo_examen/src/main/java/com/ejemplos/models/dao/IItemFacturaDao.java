package com.ejemplos.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ejemplos.models.entity.ItemFactura;

public interface IItemFacturaDao extends CrudRepository<ItemFactura, Long>{
/*
	@Query("SELECT p FROM Producto p WHERE p.nombre LIKE %?1%")
	public List<Producto> findByNombreLikeIgnoreCase(String nombreProducto);
*/
	
	//Query de prueba que devuelve los itemsFactura de Folios comprados por ALLAN!!
	@Query("SELECT i FROM ItemFactura i "
			+ "WHERE i.producto.nombre = 'Folios' "
			+ "AND i.factura.cliente.nombre = 'Allan'")
	public List<ItemFactura> findByFoliosAllan();
}
