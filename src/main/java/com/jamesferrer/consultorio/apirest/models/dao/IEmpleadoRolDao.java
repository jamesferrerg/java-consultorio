package com.jamesferrer.consultorio.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.EmpleadoRol;
import com.jamesferrer.consultorio.apirest.models.entity.Rol;

public interface IEmpleadoRolDao extends JpaRepository<EmpleadoRol, Integer>{
	
	@Query("from Rol")
	public List<Rol> findAllRoles();
	
	@Query("from Empleado")
	public List<Empleado> findAllEmpleados();
	
	/*@Query(value = "SELECT empleado_Id FROM empleados_roles GROUP BY empleado_Id HAVING COUNT(*) < 2", nativeQuery = true)
	public List<Empleado> findNotRepeatEmpleados();*/

}
