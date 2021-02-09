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
import org.springframework.web.bind.annotation.RestController;

import com.jamesferrer.consultorio.apirest.models.entity.Cita;
import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.Paciente;
import com.jamesferrer.consultorio.apirest.models.entity.Servicio;
import com.jamesferrer.consultorio.apirest.models.services.ICitaService;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class CitaRestController {
	
	@Autowired
	private ICitaService citaService;
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/citas")
	public List<Cita> index(){
		
		return citaService.findAll();
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/citas/servicios")
	public List<Servicio> listarServicio(){
		
		return citaService.findAllServicio();
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/citas/pacientes")
	public List<Paciente> listarPaciente(){
		
		return citaService.findAllPaciente();
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/citas/empleados")
	public List<Empleado> listarEmpleado(){
		
		return citaService.findAllEmpleado();
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@GetMapping("/citas/{idCita}")
	public ResponseEntity<?> show(@PathVariable Integer idCita){
		
		Cita cita = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			cita = citaService.findById(idCita);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cita == null) {
			response.put("mensaje", "La cita con ID ".concat(idCita.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cita>(cita, HttpStatus.OK);
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PostMapping("/citas")
	public ResponseEntity<?> create(@Valid @RequestBody Cita cita, BindingResult result){
		
		Cita citaNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			citaNew = citaService.save(cita);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al crear un registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La cita ha sido creada con exito");
		response.put("cita", citaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@DeleteMapping("/citas/{idCita}")
	public ResponseEntity<?> delete(@PathVariable Integer idCita){
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			citaService.delete(idCita);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El registro ha sido eliminado con éxito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@Secured({"ROLE_USER", "ROLE_ADMIN"})
	@PutMapping("/citas/{idCita}")
	public ResponseEntity<?> update(@Valid @RequestBody Cita cita, BindingResult result, @PathVariable Integer idCita){
		
		Cita citaCurrent = citaService.findById(idCita);
		Cita citaUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (citaCurrent == null) {
			response.put("mensaje", "Error: no se puede editar la cita cin id: ".concat(idCita.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			citaCurrent.setFecha(cita.getFecha());
			citaCurrent.setHora(cita.getHora());
			citaCurrent.setCosto(cita.getCosto());
			citaCurrent.setSaldo(cita.getSaldo());
			citaCurrent.setEmpleado(cita.getEmpleado());
			citaCurrent.setPaciente(cita.getPaciente());
			citaCurrent.setServicio(cita.getServicio());
			
			citaUpdate = citaService.save(citaCurrent);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La cita ha sido actualizada con éxito");
		response.put("cita", citaUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
