package com.ejemplos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ejemplos.models.entity.Cliente;
import com.ejemplos.models.service.IClienteService;

//@SessionAttributes crea un atributo de sesión, que lo eliminaremos cuando estemos en el método guardar
//se elimina con SessionStatus

@Controller
@SessionAttributes("cliente")
public class ClienteController {
	//Autoinyecta el bean
	//Es decir busca un componente registrado de Spring que lo implemente y localiza @Service
	
	@Autowired
	private IClienteService clienteService;

	//Va a llamar a la vista listar enviando el título y la lista de clientes
	//Podría ser también @GetMapping
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado de clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id > 0) {
			clienteService.delete(id);
		}
		//Redirige a listar una vez borrado
		return "redirect:/listar";
	}
	
	//EDITAR UN CLIENTE
	@RequestMapping(value="/form/{id}")
	public String editar(@PathVariable(value="id") Long id, Model model, @ModelAttribute("cliente") Cliente clienteSes) {
		Cliente cliente = null;
		
		if(id > 0) {
			cliente=clienteService.findOne(id);
		} else {
			//Redirige a listar si no existe el id
			return "redirect:/listar";
		}
		
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Editar cliente");
		
		
		System.out.println("Cliente sesion: "+ clienteSes.getApellido());

		return "form";
	}
	
	//Para guardar los datos editados del cliente
//	@RequestMapping(value="/form", method=RequestMethod.POST)
//	public String guardar(Cliente cliente) {
//		clienteService.save(cliente);
//		return "redirect:listar";
//	}
	
	//GUARDAR UN CLIENTE
	//Vamos a meter ahora validaciones
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Editar cliente");
			return "form";
		}
		
		clienteService.save(cliente);
		//Al ejecutar setComplete() elimina el objeto cliente de la sesión
		status.setComplete();
		return "redirect:listar";
	}
	
	//Crear cliente
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String crear(Model model, SessionStatus status) {
		
			//Creo un cliente vacío y machaco el de la sesión
			Cliente cliente=new Cliente();
			model.addAttribute("cliente", cliente);
			model.addAttribute("titulo", "Crear cliente");
			
			return "form";

	}
	
	
}
