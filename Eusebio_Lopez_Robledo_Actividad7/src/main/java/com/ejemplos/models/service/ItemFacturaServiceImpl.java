package com.ejemplos.models.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IItemFacturaDao;
import com.ejemplos.models.entity.ItemFactura;


//Una clase service está basada en el patrón de diseño facade
//un unico punto de acceso a clases DAO. Por cada clase DAO hay una clase Service

@Service
public class ItemFacturaServiceImpl implements IItemFacturaService{
	
	//Autoinyenctado, no vamos a tener que llamar al contstructor de itemFacturaDAO
	@Autowired
	private IItemFacturaDao itemFacturaDao;
	
	//El tratamiento de las transacciones va en la clase Service (no en los DAO)
	//también en un método Service podemos usar varios métodos DAO dentro de la misma transacción
	@Override
	//readOnly a true porque solo vamos a consultar
	@Transactional(readOnly=true)
	public List<ItemFactura> findAll(){
		return (List<ItemFactura>) itemFacturaDao.findAll();
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {
		itemFacturaDao.deleteById(id);
	}


	@Override
	@Transactional(readOnly=true)
	public ItemFactura findOne(Long id) {
		ItemFactura itemFactura=itemFacturaDao.findById(id).orElse(null);
		return itemFactura;
	}


	@Override
	@Transactional
	public void save(ItemFactura itemFactura) {
		itemFacturaDao.save(itemFactura);		
	}
	

	@Override
	public List<ItemFactura> findByFoliosAllan() {
		
		return itemFacturaDao.findByFoliosAllan();
	}

	
	
}
