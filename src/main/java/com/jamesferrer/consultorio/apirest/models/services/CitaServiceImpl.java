package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamesferrer.consultorio.apirest.models.dao.ICitaDao;
import com.jamesferrer.consultorio.apirest.models.entity.Cita;
import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.Paciente;
import com.jamesferrer.consultorio.apirest.models.entity.Servicio;

@Service
public class CitaServiceImpl implements ICitaService {
	
	@Autowired
	private ICitaDao citaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Cita> findAll() {
		
		return (List<Cita>) citaDao.findAll();
	}

	@Override
	@Transactional
	public Cita save(Cita cita) {
		
		return citaDao.save(cita);
	}

	@Override
	@Transactional
	public Cita findById(Integer idCita) {
		
		return citaDao.findById(idCita).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Integer idCita) {
		
		citaDao.deleteById(idCita);
	}

	@Override
	@Transactional
	public List<Servicio> findAllServicio() {
		
		return citaDao.findAllServicio();
	}

	@Override
	@Transactional
	public List<Paciente> findAllPaciente() {
		
		return citaDao.findAllPaciente();
	}

	@Override
	@Transactional
	public List<Empleado> findAllEmpleado() {
		
		return citaDao.findAllEmpleado();
	}

	@Override
	public Page<Cita> findAll(Pageable pageable) {
		
		return citaDao.findAll(pageable);
	}

}
