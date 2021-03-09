package com.jamesferrer.consultorio.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jamesferrer.consultorio.apirest.models.dao.IPacienteDao;
import com.jamesferrer.consultorio.apirest.models.entity.Departamento;
import com.jamesferrer.consultorio.apirest.models.entity.Municipio;
import com.jamesferrer.consultorio.apirest.models.entity.Paciente;
import com.jamesferrer.consultorio.apirest.models.entity.Sexo;
import com.jamesferrer.consultorio.apirest.models.entity.TipoIdentificacion;

@Service
public class PacienteServiceImpl implements IPacienteService{

	@Autowired
	private IPacienteDao pacienteDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Paciente> findAll() {
		
		return (List<Paciente>) pacienteDao.findAll();
	}

	@Override
	@Transactional
	public Paciente save(Paciente paciente) {

		return pacienteDao.save(paciente);
	}

	@Override
	@Transactional
	public Paciente findById(Long idPaciente) {
		
		return pacienteDao.findById(idPaciente).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long idPaciente) {
		
		pacienteDao.deleteById(idPaciente);
	}

	@Override
	@Transactional
	public List<TipoIdentificacion> findAllTipoIdentificacion() {
		
		return pacienteDao.findAllTipoIdentificacion();
	}

	@Override
	@Transactional
	public List<Sexo> findAllSexo() {
		
		return pacienteDao.findAllSexo();
	}

	@Override
	@Transactional
	public List<Departamento> findAllDepartamento() {
		
		return pacienteDao.findAllDepartamento();
	}

	@Override
	@Transactional
	public List<Municipio> findAllMunicipio() {
		
		return pacienteDao.findAllMunicipio();
	}

	@Override
	@Transactional
	public List<Paciente> findByNombreOrApellido(String term2) {
		
		return pacienteDao.findByNombreOrApellido(term2);
	}

	@Override
	@Transactional
	public Page<Paciente> findAll(Pageable pageable) {
		
		return pacienteDao.findAll(pageable);
	}
	
	

}
