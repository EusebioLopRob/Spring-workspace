package com.ejemplos.controllers;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ejemplos.models.entity.Libro;
import com.ejemplos.models.service.ILibroService;

@Controller
@SessionAttributes("libro")
public class LibroController {

	@Autowired
	private ILibroService libroService;
	
	/**
	 * Método cargarForm:
	 * Este método se encarga de limpiar el objeto libro en sesión y enviarnos a la vista
	 * del formulario. Se llama con la ruta /form, método por defecto GET, sin singún otro parámetro
	 * */
	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String cargarForm(Model model, SessionStatus status) {
		
			//Creo un Libro vacío y machaco el de la sesión
			Libro libro=new Libro();
			model.addAttribute("libro", libro);
			//Atributo para el título de la vista
			model.addAttribute("titulo", "Registro de libros");
			
			//Devolvemos la vista del formulario de introducción de libros
			return "form";
	}
	
	/**
	 * Método guardar:
	 * Este método se encarga de guardar un libro en la base de datos y de enviar al usuario
	 * a la vista de ver el nuevo libro introducido en la base de datos.
	 * Se llama con la ruta /form, método POST, sin singún otro parámetro.
	 * */
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Libro libro, BindingResult result, Model model, SessionStatus status) {
		
		//Comprobamos si existen errores de introducción de datos
		if(result.hasErrors()) {
			//Si existen errores volvemos a la vista del formulario
			model.addAttribute("titulo", "Registro de libros");
			return "form";
		}
		
		//Comprobamos si existe el isbn
		Libro comprobacion = libroService.findOne(libro.getIsbn());
		if(comprobacion != null) {
			//Si existe el libro es que hay un intento de duplicar el isbn, creamos un error acorde
			FieldError error = new FieldError("libro","isbn","No puede introducir un Isbn repetido");
			//Añadimos el error al BindingResult
			result.addError(error);
			//Volvemos a la vista del formulario
			model.addAttribute("titulo", "Registro de libros");
			return "form";
		}
		
		//Si la ejecución del método llega hasta aquí es que no se han producido errores de validación, guardamos el libro
		libroService.save(libro);
		
		//Vamos a establecer los 2 atributos de sesión que tomará la siguiente vista que vamos a cargar
		model.addAttribute("libro", libro);
		model.addAttribute("titulo", "¡Libro introducido correctamente!");
		
		//Devolvemos la vista de ver el libro introducido
		return "verLibro";
	}
}
