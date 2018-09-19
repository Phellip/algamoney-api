package com.algaworks.algamoney.api.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BeanValidationUtil {

	private static Validator validatorThreadSafe;
	
	static {
		 ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		 validatorThreadSafe = factory.getValidator();
	}
	
	public static <T> Set<ConstraintViolation<T>> validate(T tipo,Class<?>... groups) {
		return validatorThreadSafe.validate(tipo, groups);
	}
	
	public static <T> void validateOrThrowConstraintException(T tipo,Class<?>... groups) throws ConstraintViolationException {
		 Set<ConstraintViolation<T>> validate = validatorThreadSafe.validate(tipo, groups);
		 if(validate.size() > 0) {
			 throw new ConstraintViolationException(validate);
		 }
	}

}
