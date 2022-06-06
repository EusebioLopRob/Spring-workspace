package com.ejemplos.models.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IFacturaDao;
import com.ejemplos.models.entity.Factura;

//Una clase service está basada en el patrón de diseño facade
//un unico punto de acceso a clases DAO. Por cada clase DAO hay una clase Service

@Service
public class FacturaServiceImpl implements IFacturaService{
	
	//Autoinyenctado, no vamos a tener que llamar al contstructor de facturaDAO
	@Autowired
	private IFacturaDao facturaDao;
	
	//El tratamiento de las transacciones va en la clase Service (no en los DAO)
	//también en un método Service podemos usar varios métodos DAO dentro de la misma transacción
	@Override
	//readOnly a true porque solo vamos a consultar
	@Transactional(readOnly=true)
	public List<Factura> findAll(){
		return (List<Factura>) facturaDao.findAll();
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {
		facturaDao.deleteById(id);
	}


	@Override
	@Transactional(readOnly=true)
	public Factura findOne(Long id) {
		Factura factura=facturaDao.findById(id).orElse(null);
		return factura;
	}


	@Override
	@Transactional
	public void save(Factura factura) {
		facturaDao.save(factura);		
	}



	
	
	
}
