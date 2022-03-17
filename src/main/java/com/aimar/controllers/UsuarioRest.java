package com.aimar.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aimar.dao.InterfazDAO;
import com.aimar.entities.Usuario;

@RestController
@RequestMapping("usuario")
public class UsuarioRest {

	@Autowired
	private InterfazDAO interfazDAO;
	
	public InterfazDAO getRepository() {
		return interfazDAO;
	}
	
	@GetMapping
	public ResponseEntity<Usuario> crearODB() {
		Usuario usu=new Usuario();
		usu.setId(41);
		usu.setNombre("Aimar");
		usu.setApellido("Arrizabalaga");
		usu.setEdad(34);
		return ResponseEntity.ok(usu);
	}
	
	@RequestMapping("/mayor28")
	public void getMayor28() {
		Iterable<Usuario> lista = getRepository().getMayor28();
		for(Usuario usu : lista) {
			System.out.println(usu.getNombre()+", "+usu.getEdad());
		}
	}
	
	@RequestMapping("/miWeb")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("indice");
		List<Usuario> list = getRepository().findAll();
		modelAndView.addObject("miObjetoNombre",list.get(0).getNombre());
		return modelAndView;
	}
	
	@RequestMapping("/listar")
	public Iterable<Usuario> listar() {
		Iterable<Usuario> lista = getRepository().findAll();
		return lista;
	}
	
	@PostMapping("/new")
	public void newUser(@RequestBody Usuario usu) {
		interfazDAO.save(usu);
	}
	
	@RequestMapping("/verArrizabalagas")
	public Iterable<Usuario> getArrizabalagas() {
		Iterable<Usuario> lista = getRepository().findArrizabalagas();
		return lista;
	}
	
	@RequestMapping("/listarUno/{id}")
	public Optional<Usuario> listarUno(@PathVariable int id) {
		Optional<Usuario> usu = getRepository().findById(id);
		return usu;
	}
	
	@PutMapping("/actualizar/{id}/{nombre}/{edad}/{apellido}")
	public void actualizar(
			@PathVariable("id") int id, 
			@PathVariable("nombre") String nombre, 
			@PathVariable("edad") int edad, 
			@PathVariable("apellido") String apellido) {
		Usuario usu = new Usuario();
		usu.setId(id);
		usu.setNombre(nombre);
		usu.setEdad(edad);
		usu.setApellido(apellido);
		interfazDAO.save(usu);
	}
	
	@PutMapping("/modificarApellido/{id}/{apellido}")
	public void modificarApellido(
			@PathVariable("id") int id,
			@PathVariable("apellido") String apellido) {
		Optional<Usuario> usu = getRepository().findById(id);
		usu.get().setApellido(apellido);
		interfazDAO.save(usu.get());
	}
	
	
}