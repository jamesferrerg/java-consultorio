package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamesferrer.consultorio.apirest.models.dao.IEmpleadoRolDao;
import com.jamesferrer.consultorio.apirest.models.entity.Empleado;
import com.jamesferrer.consultorio.apirest.models.entity.EmpleadoRol;
import com.jamesferrer.consultorio.apirest.models.entity.Rol;

@Service
public class EmpleadoRolServiceImpl implements IEmpleadoRolService{

	@Autowired
	private IEmpleadoRolDao empleadoRolDao;
	
	@Override
	@Transactional
	public List<EmpleadoRol> findAll() {
		
		return (List<EmpleadoRol>) empleadoRolDao.findAll();
	}

	@Override
	@Transactional
	public EmpleadoRol findById(Integer idEmpleadoRol) {

		return empleadoRolDao.findById(idEmpleadoRol).orElse(null);
	}

	@Override
	@Transactional
	public EmpleadoRol save(EmpleadoRol empleadoRol) {

		return empleadoRolDao.save(empleadoRol);
	}

	@Override
	@Transactional
	public void delete(Integer idEmpleadoRol) {
		
		empleadoRolDao.deleteById(idEmpleadoRol);
	}

	@Override
	@Transactional
	public List<Rol> findAllRoles() {
		
		return empleadoRolDao.findAllRoles();
	}

	@Override
	@Transactional
	public List<Empleado> findAllEmpleados() {
		
		return empleadoRolDao.findAllEmpleados();
	}

	/*@Override
	public List<Empleado> findNotRepeatEmpleados() {
		
		return empleadoRolDao.findNotRepeatEmpleados();
	}*/

}
