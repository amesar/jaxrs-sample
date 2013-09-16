package com.amm.common.util;

import java.util.*;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import javax.validation.ConstraintViolation;

/**
 * JSR 349 Bean Validation utilities.
 */
public class ValidatorUtils {

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public static <T> void validate(T obj) {
		Set<ConstraintViolation<T>> errors = validator.validate(obj);
		if (errors.size() > 0) {
			String msg = getMessage(errors);
			throw new ValidationException(msg);
		}
	}

	public static <T> List<String> getErrors(T obj) {
		Set<ConstraintViolation<T>> violations = validator.validate(obj);
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<T> violation : violations ) 
			errors.add(""+violation.getPropertyPath()+": "+violation.getMessage());
		return errors;
	}

	public static <T> String getMessage(Set<ConstraintViolation<T>> violations) {
		if (violations.size()==0) return "";
		StringBuilder sbuf = new StringBuilder();
		for (ConstraintViolation<T> violation : violations ) {
			sbuf.append(""+violation.getPropertyPath()+": "+violation.getMessage()+"\n");
		}
		return sbuf.toString();
	}
}
