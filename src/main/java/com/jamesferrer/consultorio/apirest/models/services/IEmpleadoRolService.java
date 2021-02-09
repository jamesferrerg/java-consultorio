package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;

import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.EmpleadoRol;
import com.jamesferrer.consultorio.apirest.models.entity.Rol;

public interface IEmpleadoRolService {
	
	public List<EmpleadoRol> findAll();
	
	public EmpleadoRol findById(Integer idEmpleadoRol);
	
	public EmpleadoRol save(EmpleadoRol empleadoRol);
	
	public void delete(Integer idEmpleadoRol);
	
	public List<Rol> findAllRoles();
	
	public List<Empleado> findAllEmpleados();
	
	//public List<Empleado> findNotRepeatEmpleados();

}
