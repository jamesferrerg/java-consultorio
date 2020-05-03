package com.jamesferrer.consultorio.apirest.controllers;

//import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
/*import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;*/
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.services.IEmpleadoService;
import com.jamesferrer.consultorio.apirest.models.services.IUploadFileService;

// cross: permite compartir e integrar 2 aplicaciones que estan en diferente dominio.
@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	// Conocer como se muestra la ruta del archivo (path)
	//private final Logger log = LoggerFactory.getLogger(EmpleadoRestController.class);
	
	@GetMapping("/empleados")
	public List<Empleado> index(){
		return empleadoService.findAll();	
	}
	
	// /page/ se pasa el num. de pagina
	@GetMapping("/empleados/page/{page}")
	public Page<Empleado> index(@PathVariable Integer page){
		Pageable pageable = PageRequest.of(page, 3);
		return empleadoService.findAll(pageable);	
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
	// Antes de ingresar al metodo create se valida con al anotacion @Valid	
	// Luego inyectar en el metodo todos los mensajes de error del objeto con BindingResult
	public ResponseEntity<?> create(@Valid @RequestBody Empleado empleado, BindingResult result) {
		
		Empleado empleadoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			/* Esta es la forma anterior a jdk8
			// Se debe crear una lista que tenga los tipos de errores
			List<String> errors = new ArrayList<>();
			
			// Con lo siguiente retorna una lista de errores
			for(FieldError err: result.getFieldErrors()){
				// con getField se obtiene el nombre del campo
				errors.add("El campo '" + err.getField() +"' " + err.getDefaultMessage());
			}
			*/
			// Convertir la lista en un stream
			List<String> errors = result.getFieldErrors()
					.stream()
					/*  map: a medida que los items se estan emitiendo de los elementos tipo error, es decir
					 por cada fieldError se convierte en un String. Ademas se convierte map() de stream del tipo
					 error a un tipo String o texto. Tambien map que por cada elemento lo convertimos con funciones
					 de flecha */
					.map(err -> "El campo '" + err.getField() +"' " + err.getDefaultMessage())
					/* Ahora convertir el flujo en un String, es decir convertir de regreso al stream a un tipo 
					 list usando el collect*/
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
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
	public ResponseEntity<?> update(@Valid @RequestBody Empleado empleado, BindingResult result, @PathVariable Integer idEmpleado) {
		
		Empleado empleadoActual = empleadoService.findById(idEmpleado);
		Empleado empleadoUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()

					.map(err -> "El campo '" + err.getField() +"' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
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
			Empleado empleado = empleadoService.findById(idEmpleado);
			String nombreFotoAnterior = empleado.getFoto();
			
			uploadService.eliminar(nombreFotoAnterior);
			
			empleadoService.delete(idEmpleado);
			
		}catch(DataAccessException e){
			response.put("mensaje", "Error al eliminar el registro en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El empleado ha sido eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	// Implementacion de metodo para subir imagenes
	@PostMapping("/empleados/upload")
	public ResponseEntity<?> update(@RequestParam("archivo") MultipartFile archivo, @RequestParam("idEmpleado") Integer idEmpleado){
		
		Map<String, Object> response = new HashMap<>();
		
		Empleado empleado = empleadoService.findById(idEmpleado);
		
		if(!archivo.isEmpty()) {
			
			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiar(archivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del empleado");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			String nombreFotoAnterior = empleado.getFoto();
			uploadService.eliminar(nombreFotoAnterior);
			
			empleado.setFoto(nombreArchivo);
			empleadoService.save(empleado);
			// Mensaje a la respuesta o al response o json
			response.put("empleado", empleado);
			response.put("mensaje", "Se ha cargado correctamente la imagen: " + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Resource recurso = null;
		
		try {
			recurso = uploadService.cargar(nombreFoto);
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
		
		/* Poder pasar la cabecera de las respuesta en el http headers. Con este recurso se pueda forzar 
		 * y permita dercargarlo (attachment). Se crea o instancia el objeto cabecera */
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}

}
