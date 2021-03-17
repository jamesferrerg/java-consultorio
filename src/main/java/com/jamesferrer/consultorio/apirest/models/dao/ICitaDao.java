package com.jamesferrer.consultorio.apirest.models.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jamesferrer.consultorio.apirest.models.entity.Cita;
import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.Paciente;
import com.jamesferrer.consultorio.apirest.models.entity.Servicio;

@Repository
public interface ICitaDao extends JpaRepository<Cita, Integer> {
	
	@Query("from Servicio")
	public List<Servicio> findAllServicio();
	
	@Query("from Paciente")
	public List<Paciente> findAllPaciente();
	
	@Query("from Empleado")
	public List<Empleado> findAllEmpleado();
	
	public Page<Cita> findByFecha(Date fecha, Pageable pageable);
}
