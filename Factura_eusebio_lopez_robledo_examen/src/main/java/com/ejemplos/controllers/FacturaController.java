package com.ejemplos.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplos.models.entity.Cliente;
import com.ejemplos.models.entity.Factura;
import com.ejemplos.models.entity.ItemFactura;
import com.ejemplos.models.entity.Producto;
import com.ejemplos.models.service.IClienteService;
import com.ejemplos.models.service.IFacturaService;
import com.ejemplos.models.service.IItemFacturaService;
import com.ejemplos.models.service.IProductoService;

//@SessionAttributes crea un atributo de sesión, que lo eliminaremos cuando estemos en el método guardar
//se elimina con SessionStatus

@Controller
@SessionAttributes("factura")
public class FacturaController {
	//Autoinyecta el bean
	//Es decir busca un componente registrado de Spring que lo implemente y localiza @Service
	
	@Autowired
	private IFacturaService facturaService;
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IItemFacturaService itemFacturaService;
	
	@Autowired
	private IProductoService productoService;
		
	//Ver facturas cliente
	@RequestMapping(value="/ver/{id}")
	public String verFacturas(@PathVariable(value="id") Long id, Model model, RedirectAttributes flash) {
		Cliente cliente = null;
		List<Factura> facturas=null;
		double total=0;
		HashMap<Long, Object> totales=new HashMap<Long, Object>();
		
		if(id > 0) {
			cliente=clienteService.findOne(id);
			
			if (cliente == null) {
				//No sé bien como funciona lo de flash
				flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
				return "redirect:/listar";
			}
			else {
				facturas=cliente.getFacturas();
				for(int i=0;i<facturas.size();i++) {
					total=facturas.get(i).getTotal();
					total=(double)Math.round(total * 100d) / 100d;
					totales.put(facturas.get(i).getId(),total);
				}
			}
		} else {
			//Redirige a listar si no existe el id
			return "redirect:/listar";
		}
		
		model.addAttribute("titulo", "Detalle cliente");
		model.addAttribute("cliente", cliente);
		model.addAttribute("facturas", facturas);
		model.addAttribute("totales", totales);
		
		return "ver";
	}
	
	//ELIMINAR FACTURA
	@RequestMapping(value="/eliminarFact/{idFact}")
	public String eliminar(@PathVariable(value="idFact") Long idFact, final RedirectAttributes redirectAttributes) {
		if(idFact > 0) {
			Long clienteId = facturaService.findOne(idFact).getCliente().getId();
			facturaService.delete(idFact);
			redirectAttributes.addFlashAttribute("result", true);
			return "redirect:/ver/"+clienteId;
		}
		else {
			redirectAttributes.addFlashAttribute("result", false);
			return "redirect:/listar/";
		}		
	}
	
	//Crear Factura
	@RequestMapping(value="/crear/factura/{idCliente}")
	public String generaForm(@PathVariable(value="idCliente") Long idCliente, Model model) {
		
		
		Cliente cliente = clienteService.findOne(idCliente);
		List<Producto> productos=productoService.findByNombreLikeIgnoreCase("pa");
		Factura factura = new Factura();
		//System.out.println(cliente);
		model.addAttribute("titulo", "Crear Factura");
		model.addAttribute("depurar",false);
		model.addAttribute("cliente",cliente);
		model.addAttribute("factura",factura);
		model.addAttribute("productos",productos);
		
		return "/factura/form";
	}
	
	@RequestMapping(value="/consultarProducto2", method = RequestMethod.GET)
	public String consultarProducto2(@RequestParam(value = "nombreProducto") String nombreProducto, @RequestParam(value = "nombreProducto") String idCliente, Model model) {
		
		Long id = Long.getLong(idCliente);
		List<Producto> productos=productoService.findByNombreLikeIgnoreCase(nombreProducto);
		System.out.println(productos);
		Cliente cliente = clienteService.findOne(id);
		//System.out.println(cliente);
		model.addAttribute("titulo", "Crear Factura");
		Factura factura = new Factura();
		model.addAttribute("depurar",false);
		model.addAttribute("cliente",cliente);
		model.addAttribute("productos",productos);
		model.addAttribute("factura",factura);
		
		return "/factura/form";
	}
	
	
}
