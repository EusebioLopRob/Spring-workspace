package com.ejemplos.controllers;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplos.models.entity.Cliente;
import com.ejemplos.models.entity.Factura;
import com.ejemplos.models.service.IClienteService;
import com.ejemplos.models.service.IFacturaService;

//@SessionAttributes crea un atributo de sesión, que lo eliminaremos cuando estemos en el método guardar
//se elimina con SessionStatus

@Controller
@SessionAttributes({"factura","cliente"})
public class FacturaController {
	//Autoinyecta el bean
	//Es decir busca un componente registrado de Spring que lo implemente y localiza @Service
	
	@Autowired
	private IFacturaService facturaService;
	
	@Autowired
	private IClienteService clienteService;
		
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
	public String eliminar(@PathVariable(value="idFact") Long idFact, @ModelAttribute("cliente") Cliente cliente) {
		System.out.println("ID cliente en eliminar: "+cliente.getId());
		if(idFact > 0) {
			facturaService.delete(idFact);
		}
	
		//Redirige a listar una vez borrado
		//Aquí debería redirigir a ver/id del cliente, no de la factura, pero no sé como recuperar ese atributo en el controlador
		return "redirect:/ver/"+cliente.getId();
	}
	
	//EDITAR UNA FACTURA
		@RequestMapping(value="/crearFactura/{id}")
		public String editar(@PathVariable(value="id") Long id, Model model, @ModelAttribute("cliente") Cliente cliente) {
			Factura factura = null;
			System.out.println("Editar una factura id: "+id);
			if(id > 0) {
				factura=facturaService.findOne(id);
			} else {
				//Redirige a listar si no existe el id
				return "redirect:/ver/"+cliente.getId();
			}
			
			model.addAttribute("factura", factura);
			model.addAttribute("titulo", "Editar factura");

			return "crearFactura";
		}
	
	@RequestMapping(value="/crearFactura", method=RequestMethod.POST)
	public String guardar(@Valid Factura factura, BindingResult result, Model model, SessionStatus status, @ModelAttribute("cliente") Cliente cliente,  @ModelAttribute("factura") Factura facturaSesion) {
		long id = cliente.getId();
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Crear Factura");
			return "crearFactura";
		}
		if(facturaSesion!=null) {
			factura.setId(facturaSesion.getId());
		}
		System.out.println("Cliente crear factura 1: "+cliente.getId());
		System.out.println("Factura factura id: "+factura.getId());
		factura.setId_cliente(cliente.getId());
		facturaService.save(factura);
		return "redirect:/ver/"+cliente.getId();
	}
	
	@RequestMapping(value="/crearFactura", method=RequestMethod.GET)
	public String crear(Model model, SessionStatus status) {
		
			//Creo una factura vacia y machaco el de la sesión
			Factura factura=new Factura();
			model.addAttribute("factura", factura);
			model.addAttribute("titulo", "Crear factura");
			
			return "crearFactura";

	}
	
	@RequestMapping(value="/crearFactura/{id}", method=RequestMethod.GET)
	public String crear(@PathVariable(value="id") Long id, Model model, SessionStatus status) {
			Factura factura = facturaService.findOne(id);
			//Creo una factura vacia y machaco el de la sesión
			
			model.addAttribute("factura", factura);
			model.addAttribute("titulo", "Crear factura");
			
			return "crearFactura";

	}
	
	@RequestMapping(value="/crearFacturaCliente/{id}", method=RequestMethod.GET)
	public String crearCliente(@PathVariable(value="id") Long id, Model model, SessionStatus status) {
			Cliente cliente = clienteService.findOne(id);
			//Creo una factura vacia y machaco el de la sesión
			
			Factura factura = new Factura();
			model.addAttribute("factura", factura);
			model.addAttribute("cliente", cliente);
			model.addAttribute("titulo", "Crear factura");
			
			return "crearFactura";

	}
	
	
}
