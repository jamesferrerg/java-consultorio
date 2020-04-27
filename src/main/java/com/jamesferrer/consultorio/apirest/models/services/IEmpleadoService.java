package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;

import com.jamesferrer.consultorio.apirest.models.entity.Empleado;

public interface IEmpleadoService {
	
	public List<Empleado> findAll();
	public Empleado findById(Integer idEmpleado);
	public Empleado save(Empleado empleado);
	public void delete(Integer idEmpleado);

}
