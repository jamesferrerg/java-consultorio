package com.jamesferrer.consultorio.apirest.models.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jamesferrer.consultorio.apirest.models.entity.Cita;
import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.Paciente;
import com.jamesferrer.consultorio.apirest.models.entity.Servicio;

public interface ICitaService {
	
	public List<Cita> findAll();
	
	public Cita save(Cita cita);
	
	public Cita findById(Integer idCita);
	
	public void delete(Integer idCita);
	
	public List<Servicio> findAllServicio();
	
	public List<Paciente> findAllPaciente();
	
	public List<Empleado> findAllEmpleado();
	
	public Page<Cita> findAll(Pageable pageable);
	
	public Page<Cita> findByFecha(Date fecha, Pageable pageable);
}
