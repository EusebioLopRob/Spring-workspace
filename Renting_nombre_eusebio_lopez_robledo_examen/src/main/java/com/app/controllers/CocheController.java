package com.app.controllers;


import java.util.List;

import javax.validation.Valid;

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

import com.app.models.entity.Coche;
import com.app.models.service.ICocheService;


@Controller
@SessionAttributes("coche")
public class CocheController {
	
	@Autowired
	private ICocheService cocheService;
	
	@RequestMapping(value="/crear", method = RequestMethod.GET)
	public String cargaInicial(Model model, SessionStatus status) {
		model.addAttribute("titulo", "Registro de coches");
		model.addAttribute("registrado", false);
		model.addAttribute("coche", new Coche());
		return "crear_coche";
	}
	@RequestMapping(value="/crear", method = RequestMethod.POST)
	public String consultarByCliente(Coche coche, Model model, BindingResult result) {
		//Comprobamos si existen errores de introducción de datos
		boolean errores = false;
		if(coche.getMatricula()=="") {
			errores = true;
			//Si la matrícula viene vacía
			FieldError error = new FieldError("coche","matricula","Debe introducir una matricula");
			//Añadimos el error al BindingResult
			result.addError(error);
		}
		if(coche.getModelo()=="") {
			errores = true;
			//Si la matrícula viene vacía
			FieldError error = new FieldError("coche","modelo","Debe introducir un modelo");
			//Añadimos el error al BindingResult
			result.addError(error);
		}
		if(coche.getMarca()=="") {
			errores = true;
			//Si la matrícula viene vacía
			FieldError error = new FieldError("coche","marca","Debe introducir una marca");
			//Añadimos el error al BindingResult
			result.addError(error);
		}
		if(errores) {
			//Volvemos a la vista del formulario
			model.addAttribute("titulo", "Registro de coches");
			model.addAttribute("registrado", false);
			return "crear_coche";
		}
		//Comprobamos si existe la matricula
		Coche comprobacion = cocheService.findOne(coche.getMatricula());
		if(comprobacion != null) {
			//Si existe el coche es que hay un intento de duplicar la matrícula, creamos un error acorde
			FieldError error = new FieldError("coche","matricula","No puede introducir una matricula repetida");
			//Añadimos el error al BindingResult
			result.addError(error);
			//Volvemos a la vista del formulario
			model.addAttribute("titulo", "Registro de coches");
			model.addAttribute("registrado", false);
			return "crear_coche";
		}	
		//Si la ejecución del método ha llegado hasta aquí, es que no hay errores, guardamos el coche
		cocheService.save(coche);
		//Establecemos los atributos
		model.addAttribute("registrado", true);
		model.addAttribute("titulo", "Registro de coches");
		model.addAttribute("coche", new Coche());
		return "crear_coche";
	}
	@RequestMapping(value="/buscar", method = RequestMethod.GET)
	public String cargaEditar(Model model, SessionStatus status) {
		model.addAttribute("titulo", "Editar datos del coche");
		model.addAttribute("encontrado", false);
		model.addAttribute("actualizado", false);
		model.addAttribute("coche", new Coche());
		return "editar_coche";
	}
	
	@RequestMapping(value="/buscar", method = RequestMethod.POST)
	public String buscarCoche(Coche coche, Model model, BindingResult result) {
		//Comprobamos si existen errores de introducción de datos
		boolean errores = false;
		if(coche.getMatricula()=="") {
			errores = true;
			//Si la matrícula viene vacía
			FieldError error = new FieldError("coche","matricula","Debe introducir una matricula");
			//Añadimos el error al BindingResult
			result.addError(error);
		}
		if(errores) {
			//Volvemos a la vista del formulario
			model.addAttribute("titulo", "Editar datos del coche");
			model.addAttribute("encontrado", false);
			model.addAttribute("actualizado", false);
			return "editar_coche";
		}
		//Comprobamos si existe la matricula
		Coche comprobacion = cocheService.findOne(coche.getMatricula());
		if(comprobacion == null) {
			//Si no existe el coche es que la matricula es incorrecta
			FieldError error = new FieldError("coche","matricula","Matricula no encontrada");
			//Añadimos el error al BindingResult
			result.addError(error);
			//Volvemos a la vista del formulario
			model.addAttribute("titulo", "Editar datos del coche");
			model.addAttribute("encontrado", false);
			model.addAttribute("actualizado", false);
			return "editar_coche";
		}	
		//Si la ejecución del método ha llegado hasta aquí, es que no hay errores
		model.addAttribute("coche", comprobacion);
		model.addAttribute("titulo", "Editar datos del coche");
		model.addAttribute("encontrado", true);
		model.addAttribute("actualizado", false);
		return "editar_coche";
	}
	@RequestMapping(value="/editar", method = RequestMethod.POST)
	public String editarCoche(Coche coche, Model model, BindingResult result) {
		System.out.println(coche);
		//Comprobamos si existen errores de introducción de datos
		boolean errores = false;
		if(coche.getModelo()=="") {
			errores = true;
			//Si el modelo viene vacía
			FieldError error = new FieldError("coche","modelo","Debe introducir un modelo");
			//Añadimos el error al BindingResult
			result.addError(error);
		}
		if(coche.getMarca()=="") {
			errores = true;
			//Si la marca viene vacía
			FieldError error = new FieldError("coche","marca","Debe introducir una marca");
			//Añadimos el error al BindingResult
			result.addError(error);
		}
		if(errores) {
			//Volvemos a la vista del formulario
			model.addAttribute("titulo", "Editar datos del coche");
			model.addAttribute("encontrado", true);
			model.addAttribute("actualizado", false);
			return "editar_coche";
		}
		//Si la ejecución del método ha llegado hasta aquí, es que no hay errores
		cocheService.save(coche);
		model.addAttribute("coche", coche);
		model.addAttribute("titulo", "Editar datos del coche");
		model.addAttribute("encontrado", true);
		model.addAttribute("actualizado", true);
		return "editar_coche";
	}
	@RequestMapping(value="/eliminar/{matricula}", method = RequestMethod.GET)
	public String cargaEditar(
			Model model, 
			@PathVariable(value="matricula") String matricula,
			SessionStatus status) {
		Coche cocheEliminar = cocheService.findOne(matricula);
		//Comprobamos si el coche está alquilado
		if(cocheEliminar.isAlquilado()) {
			//Si lo está no se puede eliminar
			model.addAttribute("coche", cocheEliminar);
			model.addAttribute("titulo", "Editar datos del coche");
			model.addAttribute("encontrado", true);
			model.addAttribute("actualizado", false);
			model.addAttribute("eliminado", false);
			model.addAttribute("errorEliminacion", true);
			return "editar_coche";
		}
		cocheService.delete(matricula);
		model.addAttribute("titulo", "Editar datos del coche");
		model.addAttribute("encontrado", false);
		model.addAttribute("actualizado", false);
		model.addAttribute("eliminado", true);
		model.addAttribute("errorEliminacion", false);
		model.addAttribute("coche", new Coche());
		return "editar_coche";
	}
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String cargarLista(Model model, SessionStatus status) {
		model.addAttribute("titulo", "Listado de coches");
		//List<Coche> coches = cocheService.findAll();
		List<Coche> coches = cocheService.findByCochesAlquilados();
		model.addAttribute("coches", coches);
		return "listado";
	}
}