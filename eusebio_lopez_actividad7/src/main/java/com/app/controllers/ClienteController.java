package com.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.app.models.Cliente;



@Controller
@SessionAttributes("cliente")
public class ClienteController {
	@RequestMapping(value="/mostrar", method=RequestMethod.POST)
	public String obtenerDatos(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		//Establecemos el valor del objeto que le vamos a pasar a la otra vista
		model.addAttribute("cliente", cliente);
		//Cambiamos a la otra vista
		return "mostrarCliente";
	}
}
