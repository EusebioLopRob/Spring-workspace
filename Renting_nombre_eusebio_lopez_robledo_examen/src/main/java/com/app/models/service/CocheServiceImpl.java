package com.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.models.dao.ICocheDao;
import com.app.models.entity.Coche;

//Una clase service está basada en el patrón de diseño facade
//un unico punto de acceso a clases DAO. Por cada clase DAO hay una clase Service

@Service
public class CocheServiceImpl implements ICocheService{
	
	//Autoinyenctado, no vamos a tener que llamar al contstructor de clienteDAO
	@Autowired
	private ICocheDao cocheDao;
	
	//El tratamiento de las transacciones va en la clase Service (no en los DAO)
	//también en un método Service podemos usar varios métodos DAO dentro de la misma transacción
	@Override
	//readOnly a true porque solo vamos a consultar
	@Transactional(readOnly=true)
	public List<Coche> findAll(){
		return (List<Coche>) cocheDao.findAll();
	}
	
	
	@Override
	@Transactional
	public void delete(String matricula) {
		cocheDao.deleteById(matricula);
	}


	@Override
	@Transactional(readOnly=true)
	public Coche findOne(String matricula) {
		Coche coche=cocheDao.findById(matricula).orElse(null);
		return coche;
	}


	@Override
	@Transactional
	public void save(Coche cliente) {
		cocheDao.save(cliente);		
	}


	@Override
	public List<Coche> findByCochesAlquilados() {
		return cocheDao.findByCochesAlquilados();
	}
	
}
