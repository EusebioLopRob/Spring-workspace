package com.ejemplos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ejemplos.models.entity.Cliente;
import com.ejemplos.models.entity.Alquiler;
import com.ejemplos.models.service.IClienteService;
import com.ejemplos.models.service.IAlquilerService;


@Controller
@SessionAttributes("alquiler")
public class AlquilerController {
	@Autowired
	private IClienteService clienteService;
	@Autowired
	private IAlquilerService alquilerService;
	
	@RequestMapping(value="/listar", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Alquiler de maquinaria de Jardineria");
		model.addAttribute("alquileres", alquilerService.findAll());
		model.addAttribute("cliente", new Cliente());
		return "listar";
	}
	
}