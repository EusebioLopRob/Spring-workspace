package com.ejemplos.models.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.IProductoDao;
import com.ejemplos.models.entity.Producto;

import net.bytebuddy.asm.Advice.Return;

//Una clase service está basada en el patrón de diseño facade
//un unico punto de acceso a clases DAO. Por cada clase DAO hay una clase Service

@Service
public class ProductoServiceImpl implements IProductoService{
	
	//Autoinyenctado, no vamos a tener que llamar al constructor de productoDAO
	@Autowired
	private IProductoDao productoDao;
	
	//El tratamiento de las transacciones va en la clase Service (no en los DAO)
	//también en un método Service podemos usar varios métodos DAO dentro de la misma transacción
	@Override
	//readOnly a true porque solo vamos a consultar
	@Transactional(readOnly=true)
	public List<Producto> findAll(){
		return (List<Producto>) productoDao.findAll();
	}
	
	
	@Override
	@Transactional
	public void delete(Long id) {
		productoDao.deleteById(id);
	}


	@Override
	@Transactional(readOnly=true)
	public Producto findOne(Long id) {
		Producto producto=productoDao.findById(id).orElse(null);
		return producto;
	}


	@Override
	@Transactional
	public void save(Producto producto) {
		productoDao.save(producto);		
	}
	
	@Override
	@Transactional
	public List<Producto> findByNombreLikeIgnoreCase(String nombreProducto){
		
		List<Producto> productos=productoDao.findByNombreLikeIgnoreCase(nombreProducto);
		
		return productos;
		
	}

	
	
}
