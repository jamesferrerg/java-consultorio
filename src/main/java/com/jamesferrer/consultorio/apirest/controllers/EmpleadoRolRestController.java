package com.jamesferrer.consultorio.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jamesferrer.consultorio.apirest.models.dao.IEmpleadoDao;
import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.EmpleadoRol;
import com.jamesferrer.consultorio.apirest.models.entity.Rol;
import com.jamesferrer.consultorio.apirest.models.services.IEmpleadoRolService;
import com.jamesferrer.consultorio.apirest.models.services.IEmpleadoService;
import com.jamesferrer.consultorio.apirest.projections.EmpleadoNombre;

//import jdk.net.SocketFlow.Status;

@CrossOrigin(origins= {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class EmpleadoRolRestController {
	
	@Autowired
	private IEmpleadoRolService empleadoRolService;
	
	@Autowired
	private IEmpleadoService empleadoService;

	@GetMapping("/perfiles")
	public List<EmpleadoRol> index(){
		return empleadoRolService.findAll();
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/perfiles")
	public ResponseEntity<?> create(@Valid @RequestBody EmpleadoRol empleadoRol, BindingResult result){
		
		EmpleadoRol empleadoRolNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {

			empleadoRolNew = empleadoRolService.save(empleadoRol);

		} catch(DataAccessException e) {
			response.put("mensaje", "Error al crear el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El perfil ha sido asignado con éxito!");
		response.put("empleadoRol", empleadoRolNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/perfiles/{idEmpleadoRol}")
	public ResponseEntity<?> show(@PathVariable Integer idEmpleadoRol){
		EmpleadoRol empleadoRol = null;
		
		Map<String, Object> response = new HashMap<>();
		try {
			empleadoRol = empleadoRolService.findById(idEmpleadoRol);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (empleadoRol == null) {
			response.put("mensaje", "La asignacion del perfil con ID ".concat(idEmpleadoRol.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<EmpleadoRol>(empleadoRol, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/perfiles/{idEmpleadoRol}")
	public ResponseEntity<?> update(@Valid @RequestBody EmpleadoRol empleadoRol, BindingResult result, @PathVariable Integer idEmpleadoRol){
		
		EmpleadoRol empleadoRolActual = empleadoRolService.findById(idEmpleadoRol);
		EmpleadoRol empleadoRolUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (empleadoRolActual == null) {
			response.put("mensaje", "No se puede editar el perfil asignado ".concat(idEmpleadoRol.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			empleadoRolActual.setEmpleado(empleadoRol.getEmpleado());
			empleadoRolActual.setRol(empleadoRol.getRol());
			
			empleadoRolUpdate = empleadoRolService.save(empleadoRolActual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Se ha acutalizado el perfil con éxito!");
		response.put("empleadoRol", empleadoRolUpdate);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/perfiles/{idEmpleadoRol}")
	public ResponseEntity<?> delete (@PathVariable Integer idEmpleadoRol){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			EmpleadoRol empleadoRol = empleadoRolService.findById(idEmpleadoRol);
			empleadoRolService.delete(idEmpleadoRol);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "Se ha eliminado el perfil con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/perfiles/roles")
	public List<Rol> listarRoles(){
		
		return empleadoRolService.findAllRoles();
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/perfiles/usuarios")
	public List<Empleado> listarUsuarios(){
		
		return empleadoRolService.findAllEmpleados();
	}
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/perfiles/filtrar-empleados/{term1}")
	public List<Empleado> filtrarEmpleados(@PathVariable String term1){
		
		return empleadoService.findEmpleadoByNombre(term1);
	}
}
