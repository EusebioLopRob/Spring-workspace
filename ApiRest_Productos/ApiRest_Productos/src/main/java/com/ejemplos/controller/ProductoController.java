package com.ejemplos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.modelo.Producto;
import com.ejemplos.modelo.ProductoRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // lombok crea el constructor
public class ProductoController {

	// se declara como final pq no se va a modificar este repositorio
	private final ProductoRepositorio productoRepositorio;

	// se inyecta solo al crear el bean controlador
	// Dentro de la carpeta resources esta data.sql

	/**
	 * Obtenemos todos los productos
	 * 
	 * @return 404 si no hay productos, 200 y lista de prpoductos si hay uno o mas
	 */
	@GetMapping("/producto")
	public ResponseEntity<?> obtenerTodos() {
		List<Producto> result = productoRepositorio.findAll();

		if (result.isEmpty()) {
			// devolvemos una respuesto como instancia de ResponseEntity
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(result);
		}
	}

	/*
	 * === Obtenemos un producto en base a su ID ===
	 */
	// PathVariable: permite inyectar un fragmento de URL en una variable,
	// es decir, pasa el valor del id de la URL al método como
	// parámetro donde esté @PathVariable
	// @return 404 si no hay productos, 200 y lista de prpoductos si hay uno o mas
	@GetMapping("/producto/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable Long id) {

		Producto result = productoRepositorio.findById(id).orElse(null);

		if (result == null) {
			// devolvemos una respuesto como instancia de ResponseEntity
			return ResponseEntity.notFound().build();
		} else {
			
			return ResponseEntity.ok(result);
		}
	}

	/*
	 * === Insertamos un nuevo producto ===
	 */
	// @RequestBody permite inyectar el cuerpo de la petición en un
	// objeto, guardo en nuevo lo que venga en el body de la petición.
	@PostMapping("/producto")
	public ResponseEntity<?> nuevoProducto(@RequestBody Producto nuevo) {
		Producto n = nuevo;
		return ResponseEntity.status(HttpStatus.CREATED).body(productoRepositorio.save(n));
	}

	/*
	 * === Actualizamos un producto ===
	 */
	@PutMapping("/producto/{id}")
	public ResponseEntity<?> editarProducto(@RequestBody Producto editar, @PathVariable Long id) {
		if (productoRepositorio.existsById(id)) {
			editar.setId(id);
			return ResponseEntity.ok(productoRepositorio.save(editar));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/*
	 * === Borra producto ===
	 */
	@DeleteMapping("/producto/{id}")
	public ResponseEntity<?> borrarProducto(@PathVariable Long id) {
		if (productoRepositorio.existsById(id)) {
			productoRepositorio.deleteById(id);
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
