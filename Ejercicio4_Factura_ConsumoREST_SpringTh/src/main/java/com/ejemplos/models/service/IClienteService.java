package com.ejemplos.models.service;

import java.util.List;

import com.ejemplos.models.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	
	public Cliente findOne(Long id);
	
	public void delete(Long id);
	
	public void save(Cliente cliente);

	public List<Cliente> tienenFacturas();
}
