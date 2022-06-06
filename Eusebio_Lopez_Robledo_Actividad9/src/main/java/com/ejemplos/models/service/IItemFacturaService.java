package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.ItemFactura;

public interface IItemFacturaService {

	public List<ItemFactura> findAll();
	
	public ItemFactura findOne(Long id);
	
	public void delete(Long id);
	
	public void save(ItemFactura itemFactura);
	
	public void borrarItems(Long id);

	
}
