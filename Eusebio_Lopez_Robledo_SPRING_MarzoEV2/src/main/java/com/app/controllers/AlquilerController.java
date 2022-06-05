package com.app.controllers;


import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.Date;  
import org.springframework.validation.FieldError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.app.models.entity.Alquiler;
import com.app.models.entity.AlquilerPK;
import com.app.models.entity.Cliente;
import com.app.models.service.IAlquilerService;
import com.app.models.service.IClienteService;

@Controller
@SessionAttributes("alquiler")
public class AlquilerController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IAlquilerService alquilerService;
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model, SessionStatus status) {
		model.addAttribute("titulo", "Alquiler de maquinaria de Jardineria");
		model.addAttribute("hayCliente", false);
		model.addAttribute("alquileres", /*new ArrayList<Alquiler>()*/alquilerService.findAll());
		model.addAttribute("cliente", new Cliente());
		return "listado";
	}
	
	@RequestMapping(value="/form", method = RequestMethod.POST)
	public String consultarByCliente(Cliente cliente, Model model, BindingResult result) {
		//Comprobamos si el nombre del cliente viene vacío
		if(cliente.getNombre()=="") {
			System.out.println("No se ha introducido cliente");
			FieldError error = new FieldError("cliente","nombre","Debe introducir un nombre de cliente");
			result.addError(error);
			//Establecemos el parámetro de alquileres y hayCliente
			model.addAttribute("alquileres", alquilerService.findAll());
			model.addAttribute("hayCliente", false);
		}else {
			System.out.println("Nombre de cliente introducido: "+cliente.getNombre());
			//Buscamos el cliente
			List<Cliente> resultado = clienteService.findByNombre(cliente.getNombre());
			if(resultado.isEmpty()) {
				System.out.println("No se ha encontrado ningún cliente con ese nombre");
				FieldError error = new FieldError("cliente","nombre","No se ha encontrado ningun cliente con ese nombre");
				result.addError(error);
				//Establecemos el parámetro de alquileres y hayCliente
				model.addAttribute("alquileres", alquilerService.findAll());
				model.addAttribute("hayCliente", false);
			}else {
				System.out.println("Cliente encontrado: ");
				Cliente encontrado = resultado.get(0);
				System.out.println(encontrado);	
				//Establecemos el parámetro de alquileres y hayCliente
				model.addAttribute("alquileres", alquilerService.findByCliente(encontrado.getCodCli()));
				model.addAttribute("hayCliente", true);
			}
		}
		//Establecemos los parámetros de titulo y cliente
		model.addAttribute("titulo", "Alquiler de maquinaria de Jardineria");
		model.addAttribute("cliente", cliente);
		return "listado";
	}
	@RequestMapping(value="/eliminar/{codCli}/{codProd}/{fechainicio}")
	public String eliminar(
			@PathVariable(value="codCli") Long codCli,
			@PathVariable(value="codProd") String codProd,
			@PathVariable(value="fechainicio") String fechainicio) {
		System.out.println(fechainicio);	
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(fechainicio);
			AlquilerPK alquilerPK = new AlquilerPK(codCli,codProd,date);
			System.out.println(alquilerPK.getFechainicio());
			alquilerService.delete(alquilerPK);
			
		} catch (ParseException e) {
			System.out.println("Error de parseo");	
			e.printStackTrace();
		}  
		//Redirige a listar una vez borrado
		return "redirect:/listar";
	}
	@RequestMapping(value="/modificar/{codCli}/{codProd}/{fechainicio}")
	public String modificar(
			Model model,
			@PathVariable(value="codCli") Long codCli,
			@PathVariable(value="codProd") String codProd,
			@PathVariable(value="fechainicio") String fechainicio) {
		System.out.println(fechainicio);	
		try {
			Date date=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(fechainicio);
			AlquilerPK alquilerPK = new AlquilerPK(codCli,codProd,date);
			System.out.println(alquilerPK.getFechainicio());
			Alquiler target = alquilerService.findOne(alquilerPK);
			model.addAttribute("titulo", "Alquiler de maquinaria de Jardineria");
			model.addAttribute("alquiler", target);
			return "ver";			
		} catch (ParseException e) {
			System.out.println("Error de parseo");	
			e.printStackTrace();
			//Redirige a listar
			return "redirect:/listar";
		}  
	}
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String guardar(Alquiler alquiler, Model model, BindingResult result) {
		System.out.println("Fecha Fin");	
		System.out.println(alquiler.getFechafin());
		//Por el tema del cambio horario tenemos que añadirle una hora, no me preguntes por qué
	    java.sql.Date sqlDate = new java.sql.Date(alquiler.getFechafin().getTime()+(1000 * 60 * 60));
		alquiler.setFechafin(sqlDate);
		alquilerService.save(alquiler);
		return "redirect:/listar";
	}	
}