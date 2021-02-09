package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;

import com.jamesferrer.consultorio.apirest.models.entity.Departamento;
import com.jamesferrer.consultorio.apirest.models.entity.Municipio;
import com.jamesferrer.consultorio.apirest.models.entity.Paciente;
import com.jamesferrer.consultorio.apirest.models.entity.Sexo;
import com.jamesferrer.consultorio.apirest.models.entity.TipoIdentificacion;

public interface IPacienteService {

	public List<Paciente> findAll();
	
	public Paciente save(Paciente paciente);
	
	public Paciente findById(Long idPaciente);
	
	public void delete(Long idPaciente);
	
	public List<TipoIdentificacion> findAllTipoIdentificacion();
	
	public List<Sexo> findAllSexo();
	
	public List<Departamento> findAllDepartamento();
	
	public List<Municipio> findAllMunicipio();
}
