package com.ejemplos.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ejemplos.models.entity.Cliente;
import com.ejemplos.models.entity.Factura;
import com.ejemplos.models.entity.Producto;
import com.ejemplos.models.service.IClienteService;
import com.ejemplos.models.service.IFacturaService;
import com.ejemplos.models.service.IItemFacturaService;
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
	
	@Autowired
	private IItemFacturaService itemFacturaService;
		
	@RequestMapping(value="/crearProducto", method=RequestMethod.POST)
	public String guardar(@Valid Producto producto, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear Producto");
			return "crearProducto";
		}
		
		productoService.save(producto);
		return "redirect:/consultar";
	}
	
	@RequestMapping(value="/crearProducto/{id}", method=RequestMethod.GET)
	public String crear(@PathVariable(value="id") Long id, Model model, SessionStatus status) {
		Producto producto = null;
			//Creo una factura vacia y machaco el de la sesión
		if(id > 0) {
			producto=productoService.findOne(id);
		} else {
			//Redirige a listar si no existe el id
			return "redirect:/consultar";
		}
		
		model.addAttribute("producto", producto);
		model.addAttribute("titulo", "Editar producto");
			
		return "crearProducto";

	}
	
	@RequestMapping(value="/eliminarProducto/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id > 0) {
			itemFacturaService.borrarItems(id);
			productoService.delete(id);
		}
		//Redirige a listar una vez borrado
		return "redirect:/consultar";
	}

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
