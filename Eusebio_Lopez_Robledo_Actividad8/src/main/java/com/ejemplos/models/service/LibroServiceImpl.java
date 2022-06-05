package com.ejemplos.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ejemplos.models.dao.ILibroDao;
import com.ejemplos.models.entity.Libro;

@Service
public class LibroServiceImpl implements ILibroService{
	
	@Autowired
	private ILibroDao libroDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Libro> findAll(){
		return (List<Libro>) this.libroDao.findAll();
	}

	@Override
	public Libro findOne(String isbn) {
		Libro libro = this.libroDao.findById(isbn).orElse(null);
		return libro;
	}

	@Override
	public void delete(String isbn) {
		this.libroDao.deleteById(isbn);
	}

	@Override
	public void save(Libro libro) {
		this.libroDao.save(libro);
	}
}
