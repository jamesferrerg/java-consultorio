package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jamesferrer.consultorio.apirest.models.entity.Empleado;

public interface IEmpleadoService {

	public List<Empleado> findAll();
	
	public Page<Empleado> findAll(Pageable pageable);

	public Empleado findById(Integer idEmpleado);

	public Empleado save(Empleado empleado);

	public void delete(Integer idEmpleado);

}
