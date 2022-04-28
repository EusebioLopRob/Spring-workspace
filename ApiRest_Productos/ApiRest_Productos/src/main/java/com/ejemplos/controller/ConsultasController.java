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

import com.ejemplos.modelo.ItemFactura;
import com.ejemplos.modelo.ItemFacturaRepositorio;
import com.ejemplos.modelo.Producto;
import com.ejemplos.modelo.ProductoRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // lombok crea el constructor
public class ConsultasController {

	// se declara como final pq no se va a modificar este repositorio
	private final ProductoRepositorio productoRepositorio;
	private final ItemFacturaRepositorio itemFacturaRepositorio;

	// se inyecta solo al crear el bean controlador
	// Dentro de la carpeta resources esta data.sql

	/**
	 * Obtenemos todos los productos
	 * 
	 * @return 404 si no hay productos, 200 y lista de prpoductos si hay uno o mas
	 */
	@GetMapping("/consulta1/{anyo}")
	public ResponseEntity<?> totalFacturadoPorAnyo(@PathVariable Integer anyo) {
		List<Object> result = itemFacturaRepositorio.totalFacturadoPorAnyo(anyo);

		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/consulta2")
	public ResponseEntity<?> totalPorProducto() {
		List<Object> result = itemFacturaRepositorio.totalPorProducto();

		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/consulta3")
	public ResponseEntity<?> sinVentas() {
		List<Producto> result = itemFacturaRepositorio.sinVentas();

		return ResponseEntity.ok(result);
	}
	

}
