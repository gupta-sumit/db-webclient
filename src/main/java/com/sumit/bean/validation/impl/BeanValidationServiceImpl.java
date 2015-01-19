package com.sumit.bean.validation.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

import com.sumit.bean.validation.BeanValidationService;
import com.sumit.bean.validation.ValidationError;

public class BeanValidationServiceImpl implements BeanValidationService {

	private Map<Class<?>, Validator> validatorFactory = new HashMap<Class<?>, Validator>();
	
	@Override
	public <T> List<ValidationError> validate(T bean) throws Exception {
		if(null == bean) {
			throw new IllegalArgumentException("Argument found null");
		}
		if(!validatorFactory.containsKey(bean.getClass())) {
			throw new Exception("No validator found for " + bean.getClass());
		}
		Validator validator = validatorFactory.get(bean.getClass());
		DataBinder binder = new DataBinder(bean);
		BindingResult bindingResult = binder.getBindingResult();
		validator.validate(bean, bindingResult);
		return buildErrors(bindingResult);
	}
	
	private List<ValidationError> buildErrors(Errors errors) {
		List<ValidationError> errorList = new ArrayList<ValidationError>();
		ValidationError err = null;
		for(FieldError error: errors.getFieldErrors()) {
			err = new ValidationError(error.getCode(),error.getDefaultMessage());
			errorList.add(err);
		}
		return errorList;
	}

}
