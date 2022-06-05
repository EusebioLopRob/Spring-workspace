package com.app.models.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.models.dao.IClienteDao;
import com.app.models.entity.Cliente;

//Una clase service está basada en el patrón de diseño facade
//un unico punto de acceso a clases DAO. Por cada clase DAO hay una clase Service

@Service
public class ClienteServiceImpl implements IClienteService{
	
	//Autoinyenctado, no vamos a tener que llamar al contstructor de clienteDAO
	@Autowired
	private IClienteDao clienteDao;
	
	//El tratamiento de las transacciones va en la clase Service (no en los DAO)
	//también en un método Service podemos usar varios métodos DAO dentro de la misma transacción
	@Override
	//readOnly a true porque solo vamos a consultar
	@Transactional(readOnly=true)
	public List<Cliente> findAll(){
		return (List<Cliente>) clienteDao.findAll();
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {
		clienteDao.deleteById(id);
	}


	@Override
	@Transactional(readOnly=true)
	public Cliente findOne(Long id) {
		Cliente cliente=clienteDao.findById(id).orElse(null);
		return cliente;
	}


	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDao.save(cliente);		
	}


	@Override
	public List<Cliente> findByNombre(String nombre) {
		List<Cliente> cliente = clienteDao.findByNombre(nombre);
		return cliente;
	}
	
}
