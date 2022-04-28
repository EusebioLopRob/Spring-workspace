package com.ejemplos.modelo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ejemplos.modelo.ItemFactura;

public interface ItemFacturaRepositorio extends JpaRepository<ItemFactura, Long> {

	// Total facturado por un cliente dado un año y un nombre
	@Query("SELECT i.factura.cliente.id, i.factura.cliente.nombre, i.factura.cliente.apellido, SUM(i.cantidad*i.producto.precio), COUNT(i.factura) "
			+ "FROM ItemFactura i " + "WHERE YEAR(i.factura.createAt) = ?1 "
			+ "GROUP BY i.factura.cliente.id, i.factura.cliente.nombre, i.factura.cliente.apellido")
	public List<Object> totalFacturadoPorAnyo(Integer anyo);

	// Total facturado por producto ordenados de más nuevos a más viejos
	@Query("SELECT i.producto.id, i.producto.nombre, i.producto.createAt, "
			+ "COUNT(i.producto), SUM(i.cantidad), ROUND(i.producto.precio*(SUM(i.cantidad)),2)" + "FROM ItemFactura i "
			+ "GROUP BY i.producto.id, i.producto.nombre, i.producto.createAt " + "ORDER BY i.producto.createAt DESC")
	public List<Object> totalPorProducto();

	// Productos que no se han vendido
	@Query("SELECT p "
			+ "FROM Producto p  " 
			+ "WHERE p.id NOT IN (SELECT i.producto.id FROM ItemFactura i)")
	public List<Producto> sinVentas();

}
