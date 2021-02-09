package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jamesferrer.consultorio.apirest.models.entity.Cargo;
import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.Sexo;
import com.jamesferrer.consultorio.apirest.models.entity.TipoIdentificacion;

public interface IEmpleadoService{

	public List<Empleado> findAll();
	
	public Page<Empleado> findAll(Pageable pageable);

	public Empleado findById(Integer idEmpleado);

	public Empleado save(Empleado empleado);

	public void delete(Integer idEmpleado);
	
	public List<TipoIdentificacion> findAllTiposIdentificacion();
	
	public Empleado findByUsername(String username);
	
	public List<Sexo> findAllSexos();
	
	public List<Empleado> findEmpleadoByNombre (String term1);
	
	public <T> List<T> findNotRepeatEmpleado(String term1, Class<T> type);

	public List<Cargo> findAllCargos();
}
