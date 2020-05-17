package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.TipoIdentificacion;

public interface IEmpleadoService{

	public List<Empleado> findAll();
	
	public Page<Empleado> findAll(Pageable pageable);

	public Empleado findById(Integer idEmpleado);

	public Empleado save(Empleado empleado);

	public void delete(Integer idEmpleado);
	
	public List<TipoIdentificacion> findAllTiposIdentificacion();
	
	public Empleado findByUsername(String username);

}
