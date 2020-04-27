package com.jamesferrer.consultorio.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.jamesferrer.consultorio.apirest.models.entity.Empleado;

public interface IEmpleadoDao extends CrudRepository<Empleado, Integer>{

}
