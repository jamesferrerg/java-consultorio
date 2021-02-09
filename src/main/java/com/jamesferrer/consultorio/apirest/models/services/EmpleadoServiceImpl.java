package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamesferrer.consultorio.apirest.models.dao.IEmpleadoDao;
import com.jamesferrer.consultorio.apirest.models.entity.Cargo;
import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.Sexo;
import com.jamesferrer.consultorio.apirest.models.entity.TipoIdentificacion;
import com.jamesferrer.consultorio.apirest.projections.EmpleadoNombre;

//antes de realizar el jwt estaba sin el implements
//se usa el implements para implementar el login springsegurity
@Service
public class EmpleadoServiceImpl implements IEmpleadoService, UserDetailsService{

	// injectar el empleadoDao 
	@Autowired
	private IEmpleadoDao empleadoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return (List<Empleado>) empleadoDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Empleado> findAll(Pageable pageable) {

		return empleadoDao.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findById(Integer idEmpleado) {
		
		return empleadoDao.findById(idEmpleado).orElse(null);
	}

	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		
		return empleadoDao.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Integer idEmpleado) {
		
		empleadoDao.deleteById(idEmpleado);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoIdentificacion> findAllTiposIdentificacion() {
		
		return empleadoDao.findAllTiposIdentificacion();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Sexo> findAllSexos() {
		return empleadoDao.findAllSexos();
	}
	
	// inicio jwt
	
	// manejo de error al iniciar sesion o login
	private Logger logger = LoggerFactory.getLogger(EmpleadoServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Empleado empleado = empleadoDao.findByUsername(username);
		
		if(empleado == null) {
			logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		
		// Debe convertirse Roles en un tipo GrantedAithority con stream...
		List<GrantedAuthority> authorities = empleado.getRoles()
				.stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNombreRol()))
				.peek(authority -> logger.info("Rol: "+ authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(empleado.getUsername(), empleado.getPassword(), empleado.getHabilitado(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findByUsername(String username) {
		
		return empleadoDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findEmpleadoByNombre(String term1) {
		// puede seleccionar findByNombreOrApellido o findByNombreOrApellidoContainingIgnoreCase
		return empleadoDao.findByNombre(term1);
	}
	
	// fin jwt
	
	@Override
	@Transactional(readOnly = true)
	public <T> List<T> findNotRepeatEmpleado(String term1, Class<T> type) {
		
		return (List<T>)empleadoDao.findNotRepeatEmpleado(term1, EmpleadoNombre.class);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Cargo> findAllCargos(){
		return empleadoDao.findAllCargos();
	}

}
