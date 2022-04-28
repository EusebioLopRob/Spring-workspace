package com.ejemplos.models.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IMarcaDao;
import com.ejemplos.models.entity.Marca;

//Una clase service está basada en el patrón de diseño facade
//un unico punto de acceso a clases DAO. Por cada clase DAO hay una clase Service

@Service
public class MarcaServiceImpl implements IMarcaService{
	
	//Autoinyenctado, no vamos a tener que llamar al contstructor de clienteDAO
	@Autowired
	private IMarcaDao marcaDao;
	
	//El tratamiento de las transacciones va en la clase Service (no en los DAO)
	//también en un método Service podemos usar varios métodos DAO dentro de la misma transacción
	@Override
	//readOnly a true porque solo vamos a consultar
	@Transactional(readOnly=true)
	public List<Marca> findAll(){
		return (List<Marca>) marcaDao.findAll();
	}
	
	
	@Override
	@Transactional
	public void delete(String id) {
		marcaDao.deleteById(id);
	}


	@Override
	@Transactional(readOnly=true)
	public Marca findOne(String id) {
		Marca marca=marcaDao.findById(id).orElse(null);
		return marca;
	}


	@Override
	@Transactional
	public void save(Marca marca) {
		marcaDao.save(marca);		
	}
	
}