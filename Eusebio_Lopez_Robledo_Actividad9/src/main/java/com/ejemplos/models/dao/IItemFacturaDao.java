package com.ejemplos.models.dao;



import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.entity.Cliente;
import com.ejemplos.models.entity.ItemFactura;

public interface IItemFacturaDao extends CrudRepository<ItemFactura, Long>{

//	@Query("DELETE FROM items i WHERE i.id_producto=?1")
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM ItemFactura i WHERE i.producto.id  = :id")
	public void borrarItems(Long id);
	
}

