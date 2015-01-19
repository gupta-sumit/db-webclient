package com.sumit.dbclient.ws.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.sumit.bean.validation.BeanValidationService;
import com.sumit.bean.validation.ValidationError;
import com.sumit.dbclient.DatabaseConfiguration;
import com.sumit.dbclient.services.DatabaseMetaDataService;
import com.sumit.dbclient.ws.DatabaseConfigurationService;
import com.sumit.dbclient.ws.beans.DBConfiguration;
import com.sumit.dbclient.ws.beans.DBConfigurations;
import com.sumit.dbclient.ws.beans.Error;
import com.sumit.dbclient.ws.beans.Request;
import com.sumit.dbclient.ws.beans.Response;

public class DatabaseConfigurationServiceImpl implements DatabaseConfigurationService{

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfigurationServiceImpl.class);
	
	@Autowired
	private DatabaseMetaDataService dbMetaDataService;
	
	@Autowired
	private BeanValidationService beanValidationService;
	
	@Override
	public Response saveDBConfiguration(Request request) {
		try {
			List<ValidationError> errors = beanValidationService.validate(request.getBody());
			if(CollectionUtils.isEmpty(errors)) {
				dbMetaDataService.initialize(DBConfiguration.createDatabaseConfiguration((DBConfiguration) request.getBody()));
			} else {
				return Response.failureResponse(buildErrors(errors));
			}
		} catch(Exception e) {
			LOGGER.error("Error occured while saving configuration {}",request.getBody(),e);
			return Response.internalServerError();
		}
		return Response.successResponse();
	}

	@Override
	public Response getAllConfigurations() {
		DBConfigurations dbConfigurations = new DBConfigurations();
		List<DBConfiguration> configList = new ArrayList<DBConfiguration>();
		for (DatabaseConfiguration configuration : dbMetaDataService.getAllConfiguration()) {
			DBConfiguration cfgBean = DBConfiguration.newInstance(configuration);
			configList.add(cfgBean);
		}
		dbConfigurations.setDatabaseConfigurations(configList);
		return Response.successResponse(dbConfigurations);
	}
	
	private List<Error> buildErrors(List<ValidationError> errors) {
		List<Error> genericErrors = new ArrayList<Error>();
		for(ValidationError error : errors) {
			genericErrors.add(new Error(error.getCode(), error.getMessage()));
		}
		return genericErrors;
	}

}
