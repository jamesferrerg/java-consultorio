package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamesferrer.consultorio.apirest.models.dao.IEmpleadoDao;
import com.jamesferrer.consultorio.apirest.models.entity.Empleado;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService{

	// injectar el empleadoDao 
	@Autowired
	private IEmpleadoDao empleadoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return (List<Empleado>) empleadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Empleado findById(Integer idEmpleado) {
		
		return empleadoDao.findById(idEmpleado).orElse(null);
	}

	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		
		return empleadoDao.save(empleado);
	}

	@Override
	@Transactional
	public void delete(Integer idEmpleado) {
		
		empleadoDao.deleteById(idEmpleado);
		
	}

}
