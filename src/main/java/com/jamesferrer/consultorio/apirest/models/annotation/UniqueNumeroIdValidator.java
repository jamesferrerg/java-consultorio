package com.jamesferrer.consultorio.apirest.models.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.jamesferrer.consultorio.apirest.models.dao.IEmpleadoDao;

public class UniqueNumeroIdValidator implements ConstraintValidator<UniqueNumeroIdentificacion, String>{

	@Autowired
	private IEmpleadoDao empleadoDao;
	
	@Override
	public void initialize(UniqueNumeroIdentificacion constraintAnnotation) {
	}
	
	@Override
	public boolean isValid(String numeroIdentificacion, ConstraintValidatorContext context) {
		if (empleadoDao == null) {
			return true;
		}
		return empleadoDao.findByNumeroIdentificacion(numeroIdentificacion) == null;
	}

}
