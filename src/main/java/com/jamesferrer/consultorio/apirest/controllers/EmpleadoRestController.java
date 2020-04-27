package com.jamesferrer.consultorio.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.services.IEmpleadoService;

// cross: permite compartir e integrar 2 aplicaciones que estan en diferente dominio.
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping("/empleados")
	public List<Empleado> index(){
		return empleadoService.findAll();	
		
	}
	
	@GetMapping("/empleados/{idEmpleado}")
	/* Clase ResponseEntity: Un componente o clase que maneja un mensaje de error y poder 
	pasar nuestro objeto, la clase entity a la respuesta al responseBody. Cambiando de la siguiente manera
	Se cambia Empleado por ResponseEntity<T> donde T es el tipo de dato <Empleado>, tambien siendo T
	cualquier tipo de datos, un map, un json etc por lo que se recomienta el ? que es cualquier tipo
	de objeto*/
	public ResponseEntity<?> show(@PathVariable Integer idEmpleado) {
		/* Try-catch: Otro tipo de errores como SQL, conexiones, sintaxis o cualquier 
		problema que se genera en el servidor - DataAccesException*/
		Empleado empleado = null;
		//Map: almacena objetos o valores asociada a un nombre y asigna un mensaje de error
		Map<String, Object> response = new HashMap<>();
		try {
			empleado = empleadoService.findById(idEmpleado);
		} catch(DataAccessException e){
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(empleado == null) {
			response.put("mensaje", "El empleado con ID: ".concat(idEmpleado.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		// Con esto <> indicamos el tipo de dato
		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
	}
	
	@PostMapping("/empleados")
	public ResponseEntity<?> create(@RequestBody Empleado empleado) {
		
		Empleado empleadoNew = null;
		Map<String, Object> response = new HashMap<>();
		try {
			empleadoNew = empleadoService.save(empleado);
		}catch(DataAccessException e){
			response.put("mensaje", "Error en crear el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El empleado ha sido creado con éxito!");
		response.put("empleado", empleadoNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/empleados/{idEmpleado}")
	public ResponseEntity<?> update(@RequestBody Empleado empleado, @PathVariable Integer idEmpleado) {
		
		Empleado empleadoActual = empleadoService.findById(idEmpleado);
		Empleado empleadoUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if(empleadoActual == null) {
			response.put("mensaje", "Error: no se pudo editar del empleado con ID: ".concat(idEmpleado.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
		empleadoActual.setNombre(empleado.getNombre());
		empleadoActual.setApellido(empleado.getApellido());
		empleadoActual.setNumeroIdentificacion(empleado.getNumeroIdentificacion());
		empleadoActual.setCelular(empleado.getCelular());
		empleadoActual.setTelefono(empleado.getTelefono());
		empleadoActual.setEmail(empleado.getEmail());
		empleadoActual.setFecha_Contrato(empleado.getFechaContrato());
		
		empleadoUpdate = empleadoService.save(empleadoActual);
		}catch(DataAccessException e){
			response.put("mensaje", "Error al actualizar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El empleado ha sido actualizado con éxito!");
		response.put("empleado", empleadoUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/empleados/{idEmpleado}")
	public ResponseEntity<?> delete(@PathVariable Integer idEmpleado) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			empleadoService.delete(idEmpleado);
		}catch(DataAccessException e){
			response.put("mensaje", "Error al eliminar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El empleado ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

}
