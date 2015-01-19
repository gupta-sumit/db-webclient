package com.sumit.bean.validation;

import java.util.List;

public interface BeanValidationService {

	public <T> List<ValidationError> validate(T bean) throws Exception;
	
}
