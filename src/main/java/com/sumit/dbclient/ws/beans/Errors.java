package com.sumit.dbclient.ws.beans;

public enum Errors {

	INTERNAL_SERVER_ERROR("500","Internal Server Error Occured.");
	
	private String code;
	private String message;
	
	Errors(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public Error getError() {
		return new Error(code,message);
	}
	
}
