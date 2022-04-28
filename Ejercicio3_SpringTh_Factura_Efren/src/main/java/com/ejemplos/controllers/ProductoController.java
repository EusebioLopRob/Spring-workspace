package com.ejemplos.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ejemplos.models.entity.Cliente;
import com.ejemplos.models.entity.Producto;
import com.ejemplos.models.service.IClienteService;
import com.ejemplos.models.service.IFacturaService;
import com.ejemplos.models.service.IProductoService;

//@SessionAttributes crea un atributo de sesión, que lo eliminaremos cuando estemos en el método guardar
//se elimina con SessionStatus

@Controller
@SessionAttributes("producto")
public class ProductoController {
	//Autoinyecta el bean
	//Es decir busca un componente registrado de Spring que lo implemente y localiza @Service
	
	@Autowired
	private IFacturaService facturaService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IProductoService productoService;
		

	@RequestMapping(value="/consultar", method = RequestMethod.GET)
	public String consultar(Model model) {
		
		//Este lo hice de prueba antes de hacer la consulta para ver si mostraba correctamente la lista de todos los productos
		List<Producto> productos=new ArrayList<Producto>();
		//Lo comento para que me muestre la lista vacía al acceder a la página
		//productos=productoService.findAll();
		model.addAttribute("productos",productos);
		
		List<Cliente> clientes=new ArrayList<Cliente>();
		//Lo comento para que me muestre la lista vacía al acceder a la página
		clientes=clienteService.tienenFacturas();
		System.out.println("Clientes: "+clientes);
		model.addAttribute("clientes", clientes);
		
		model.addAttribute("titulo", "Consultar");
		
		return "consultar";
	}
	
	@RequestMapping(value="/consultarProducto", method = RequestMethod.GET)
	public String consultarProducto(@RequestParam(value = "nombreProducto") String nombreProducto, Model model) {
		System.out.println(nombreProducto);
		
		List<Producto> productos=productoService.findByNombreLikeIgnoreCase(nombreProducto);
		System.out.println(productos);
		
		model.addAttribute("titulo", "Consultar");
		model.addAttribute("productos",productos);

		
		return "consultar";
	}
	
}
