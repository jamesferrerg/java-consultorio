package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;

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
}
