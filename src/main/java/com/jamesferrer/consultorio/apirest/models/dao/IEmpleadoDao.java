package com.jamesferrer.consultorio.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.TipoIdentificacion;

public interface IEmpleadoDao extends JpaRepository<Empleado, Integer>{
	
	@Query("from TipoIdentificacion")
	public List<TipoIdentificacion> findAllTiposIdentificacion();
	
	// La opcion recomendad es findByUsername pero en la base de datos no hay atributo username sino email
	public Empleado findByUsername(String username);
}
