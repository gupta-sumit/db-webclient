package com.sumit.dbclient.ws.beans;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {

	@XmlAttribute(name="Status")
	private ResponseStatus status;
	
	private Object body;
	
	@XmlElementWrapper(name="Errors")
	@XmlElement(name="Error")
	private List<Error> errors;

	private Response() {
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public Object getBody() {
		return body;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public static Response successResponse(Object body) {
		Response response = new Response();
		response.status = ResponseStatus.SUCCESS;
		response.body = body;
		return response;
	}

	public static Response successResponse() {
		Response response = new Response();
		response.status = ResponseStatus.SUCCESS;
		return response;
	}

	public static Response failureResponse(List<Error> errors) {
		Response response = new Response();
		response.status = ResponseStatus.SUCCESS;
		response.errors = errors;
		return response;
	}
	
	public static Response internalServerError() {
		Response response = new Response();
		response.status = ResponseStatus.SUCCESS;
		List<Error> errors = new ArrayList<Error>();
		errors.add(Errors.INTERNAL_SERVER_ERROR.getError());
		response.errors = errors;
		return response;
	}
	
	public enum ResponseStatus {
		SUCCESS,
		FAIL
	}
	
}
