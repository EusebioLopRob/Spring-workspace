package com.ejemplos.models.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IAlquilerDao;
import com.ejemplos.models.entity.Alquiler;
import com.ejemplos.models.entity.AlquilerPK;

//Una clase service está basada en el patrón de diseño facade
//un unico punto de acceso a clases DAO. Por cada clase DAO hay una clase Service

@Service
public class AlquilerServiceImpl implements IAlquilerService{
	
	//Autoinyenctado, no vamos a tener que llamar al contstructor de clienteDAO
	@Autowired
	private IAlquilerDao alquilerDao;
	
	//El tratamiento de las transacciones va en la clase Service (no en los DAO)
	//también en un método Service podemos usar varios métodos DAO dentro de la misma transacción
	@Override
	//readOnly a true porque solo vamos a consultar
	@Transactional(readOnly=true)
	public List<Alquiler> findAll(){
		return (List<Alquiler>) alquilerDao.findAll();
	}
	
	
	@Override
	@Transactional
	public void delete(AlquilerPK id) {
		alquilerDao.deleteById(id);
	}


	@Override
	@Transactional(readOnly=true)
	public Alquiler findOne(AlquilerPK id) {
		Alquiler alquiler=alquilerDao.findById(id).orElse(null);
		return alquiler;
	}


	@Override
	@Transactional
	public void save(Alquiler alquiler) {
		alquilerDao.save(alquiler);		
	}
	
}
