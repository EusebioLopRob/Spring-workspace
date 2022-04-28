package com.ejemplos.models.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IJardineriaDao;
import com.ejemplos.models.entity.Jardineria;

//Una clase service está basada en el patrón de diseño facade
//un unico punto de acceso a clases DAO. Por cada clase DAO hay una clase Service

@Service
public class JardineriaServiceImpl implements IJardineriaService{
	
	//Autoinyenctado, no vamos a tener que llamar al contstructor de clienteDAO
	@Autowired
	private IJardineriaDao jardineriaDao;
	
	//El tratamiento de las transacciones va en la clase Service (no en los DAO)
	//también en un método Service podemos usar varios métodos DAO dentro de la misma transacción
	@Override
	//readOnly a true porque solo vamos a consultar
	@Transactional(readOnly=true)
	public List<Jardineria> findAll(){
		return (List<Jardineria>) jardineriaDao.findAll();
	}
	
	
	@Override
	@Transactional
	public void delete(String id) {
		jardineriaDao.deleteById(id);
	}


	@Override
	@Transactional(readOnly=true)
	public Jardineria findOne(String id) {
		Jardineria jardineria=jardineriaDao.findById(id).orElse(null);
		return jardineria;
	}


	@Override
	@Transactional
	public void save(Jardineria jardineria) {
		jardineriaDao.save(jardineria);		
	}
	
}
