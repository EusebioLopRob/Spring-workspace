package com.ejemplos.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ejemplos.models.entity.ItemFactura;
import com.ejemplos.models.entity.Producto;
import com.fasterxml.jackson.databind.ObjectMapper;

//@SessionAttributes crea un atributo de sesión, que lo eliminaremos cuando estemos en el método guardar
//se elimina con SessionStatus

@Controller
@SessionAttributes("producto")
public class ApiRestProductosController {

	// Objeto cliente para gestionar las peticiones:
	private Client client;

	// Jackson serializer: Sirve para serializar y deserializar los JSON
	// que nos va a devolver.
	private ObjectMapper mapper = new ObjectMapper();

	public ApiRestProductosController() {
		super();
		this.client = ClientBuilder.newClient();
	}

	/*
	 * *******************************************************************
	 * ****************** LISTAR TODOS PRODUCTOS *************************
	 */
	// Va a llamar a la vista listar enviando el título
	// Podría ser también @GetMapping
	@GetMapping(value = "estadisticas")
	public String listarProductos(Model model, RedirectAttributes flash) {
		try {
			String resultado = this.client.target("http://localhost:8099/producto").request(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON).get(String.class);
			// Convertir JSON array string en lista de objetos con el serializador
			List<Producto> productos = Arrays.asList(mapper.readValue(resultado, Producto[].class));
			model.addAttribute("productos", productos);
			model.addAttribute("titulo", "Productos");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "estadisticas";
	}

	/*
	 * *******************************************************************
	 * ******************** CREAR UN PRODUCTO ***************************
	 */
	@GetMapping(value = "/crear")
	public String cargarProdVacio(Model model, SessionStatus status) {

		// Creo un cliente vacío y machaco el de la sesión
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		model.addAttribute("titulo", "Crear producto");

		return "crear";

	}

	/*
	 * *******************************************************************
	 * ******************** CREAR UN PRODUCTO ***************************
	 */
	@PostMapping(value = "/crear")
	public String crearProd(@Valid Producto producto, BindingResult result, Model model, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Crear producto");
			return "crear";
		}

		Response resultado = this.client.target("http://localhost:8099/producto").request()
				.accept(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(producto, MediaType.APPLICATION_JSON_TYPE));

		status.setComplete();
		return "redirect:estadisticas";
	}

	/*
	 * *******************************************************************
	 * ***************** CARGAR UN PRODUCTO PARA EDITAR ******************
	 */

	@GetMapping(value = "/editar/{id}")
	public String cargar(@PathVariable(value = "id") Long id, Model model) {
		Producto producto = null;

		try {
			String resultado = this.client.target("http://localhost:8099/producto/" + id)
					.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);

			producto = mapper.readValue(resultado, Producto.class);
			model.addAttribute("producto", producto);
			model.addAttribute("titulo", "Editar " + producto.getNombre());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "editar";
	}

	/*
	 * *******************************************************************
	 * ******************** GUARDAR UN PRODUCTO EDITADO ***************************
	 */
	@PostMapping(value = "/editar")
	public String guardar(@Valid Producto producto, BindingResult result, Model model, SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("titulo", "Editar producto");
			return "editar";
		}

		Response resultado = this.client.target("http://localhost:8099/producto/" + producto.getId()).request()
				.accept(MediaType.APPLICATION_JSON).put(Entity.entity(producto, MediaType.APPLICATION_JSON));

		status.setComplete();
		return "redirect:estadisticas";
	}

	/*
	 * *******************************************************************
	 * ****************** BORRAR PRODUCTO POR ID *************************
	 */
	@RequestMapping(value = "/estadisticas/eliminar/{id}", method = RequestMethod.GET)
	public String borrarProducto(@PathVariable(value = "id") Long id, Model model,
			final RedirectAttributes redirectAttributes) {
		try {
			String resultado = this.client.target("http://localhost:8099/producto/" + id)
					.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).delete(String.class);
			redirectAttributes.addFlashAttribute("result", true);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/estadisticas/";
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	/*
	 * ######################################################## # # CONSULTAS
	 * PERSONALIZADAS # ########################################################
	 */

	// Consulta 1: Dado un año, devolver el total facturado por los clientes.
	@GetMapping(value = "/consulta1")
	public String cargarConsulta1(Model model) {
		List<Object> result = new ArrayList<Object>();
		try {
			model.addAttribute("result", result);
			model.addAttribute("titulo", "CONSULTA 1: TOTALES POR AÑO");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "consulta1";
	}

	@PostMapping(value = "/consulta1")
	public String consulta1(@RequestParam(value = "anyo") Integer anyo, Model model) {

		try {
			String resultado = this.client.target("http://localhost:8099/consulta1/" + anyo)
					.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);
			// Convertir JSON array string en lista de objetos con el serializador
			List<Object> result = Arrays.asList(mapper.readValue(resultado, Object[].class));
			if (result == null) {
				result = new ArrayList<Object>();
			}
			model.addAttribute("result", result);
			model.addAttribute("titulo", "CONSULTA 1: TOTALES POR AÑO");
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "consulta1";
	}
	
	@GetMapping(value = "/consulta2")
	public String consulta2(Model model) {

		try {
			String resultado = this.client.target("http://localhost:8099/consulta2")
					.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);
			
			// Convertir JSON array string en lista de objetos con el serializador
			List<Object> result = Arrays.asList(mapper.readValue(resultado, Object[].class));
			if (result == null) {
				result = new ArrayList<Object>();
			}
			model.addAttribute("result", result);
			model.addAttribute("titulo", "CONSULTA 2: TOTAL POR PRODUCTO");
			model.addAttribute("subtitulo", "Ordenados por más novedosos");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "consulta2";
	}
	
	@GetMapping(value = "/consulta3")
	public String consulta3(Model model) {

		try {
			String resultado = this.client.target("http://localhost:8099/consulta3")
					.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).get(String.class);
			
			// Convertir JSON array string en lista de objetos con el serializador
			List<Producto> productos = Arrays.asList(mapper.readValue(resultado, Producto[].class));
			if (productos == null) {
				productos = new ArrayList<Producto>();
			}
			model.addAttribute("productos", productos);
			model.addAttribute("titulo", "CONSULTA 3: PRODUCTOS JAMÁS VENDIDOS");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "consulta3";
	}
}
