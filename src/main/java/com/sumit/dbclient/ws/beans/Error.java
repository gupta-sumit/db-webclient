package com.sumit.dbclient.ws.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"code","message"})
public class Error {

	@XmlAttribute(name="Code")
	private String code;
	
	@XmlAttribute(name="Message")
	private String message;
	
	public Error() {}
	
	public Error(String code, String message) {
		this.code = code;
		this.message = message;
	}
	 
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
}
