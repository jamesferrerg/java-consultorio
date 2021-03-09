package com.jamesferrer.consultorio.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jamesferrer.consultorio.apirest.models.entity.Cargo;
import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.Sexo;
import com.jamesferrer.consultorio.apirest.models.entity.TipoIdentificacion;

@Repository
public interface IEmpleadoDao extends JpaRepository<Empleado, Integer>{
	
	@Query("from TipoIdentificacion")
	public List<TipoIdentificacion> findAllTiposIdentificacion();
	
	// La opcion recomendad es findByUsername pero en la base de datos no hay atributo username sino email
	public Empleado findByUsername(String username);
	
	@Query("from Sexo")
	public List<Sexo> findAllSexos();
	
	@Query("select e from Empleado e where e.nombre like %?1% or e.apellido like %?1%")
	public List<Empleado> findByNombreOrApellido (String term1);
	
	@Query("select e from Empleado e where e.nombre like %?1% and e.cargo=3")
	public List<Empleado> findByNombreDr (String term1);
	
	/*
	public List<Empleado> findByNombreOrApellidoContainingIgnoreCase (String term1, String term2);*/
	
	/*@Query(value = "SELECT nombre FROM empleados AS e WHERE e.nombre like %?1%", nativeQuery = true)
	@Query(value = "SELECT nombre FROM (SELECT nombre FROM empleados e LEFT JOIN empleados_roles er ON e.id_empleado = er.empleado_Id WHERE nombre like %?1% GROUP BY nombre HAVING count(empleado_Id) <= 1) val", nativeQuery = true)
	public List<Empleado> findNotRepeatEmpleado (String term1);*/
	
	 @Query(value="SELECT nombre FROM(SELECT nombre FROM empleados e LEFT JOIN empleados_roles er ON e.id_empleado = er.empleado_Id WHERE nombre like %?1% GROUP BY nombre HAVING count(empleado_Id) <= 1) val", nativeQuery=true)
	 <T> List<T> findNotRepeatEmpleado(String term1, Class<T> type);
	 
	 public Empleado findByNumeroIdentificacion(String numeroIdentificacion);
	 
	 @Query("from Cargo")
	 public List<Cargo> findAllCargos();
	 
}
