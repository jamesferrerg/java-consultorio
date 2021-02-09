package com.jamesferrer.consultorio.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jamesferrer.consultorio.apirest.models.entity.Departamento;
import com.jamesferrer.consultorio.apirest.models.entity.Municipio;
import com.jamesferrer.consultorio.apirest.models.entity.Paciente;
import com.jamesferrer.consultorio.apirest.models.entity.Sexo;
import com.jamesferrer.consultorio.apirest.models.entity.TipoIdentificacion;

public interface IPacienteDao extends JpaRepository<Paciente, Long>{
	
	@Query("from TipoIdentificacion")
	public List<TipoIdentificacion> findAllTipoIdentificacion();
	
	@Query("from Sexo")
	public List<Sexo> findAllSexo();
	
	@Query("from Departamento")
	public List<Departamento> findAllDepartamento();
	
	@Query("from Municipio")
	public List<Municipio> findAllMunicipio();
	
	public Paciente findByNumeroIdentificacion(String numeroIdentificacion);

}
