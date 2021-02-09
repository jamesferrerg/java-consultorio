package com.jamesferrer.consultorio.apirest.models.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.jamesferrer.consultorio.apirest.models.dao.IPacienteDao;

public class UniqueNumIdentPacienteValidator implements ConstraintValidator<UniqueNumIdentPaciente, String>{

	@Autowired
	private IPacienteDao pacienteDao;
	
	@Override
	public void initialize(UniqueNumIdentPaciente constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(String numeroIdentificacion, ConstraintValidatorContext context) {
		
		return pacienteDao.findByNumeroIdentificacion(numeroIdentificacion) == null;
	}

}
