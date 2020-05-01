package com.jamesferrer.consultorio.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jamesferrer.consultorio.apirest.models.entity.Empleado;

public interface IEmpleadoDao extends JpaRepository<Empleado, Integer>{

}
