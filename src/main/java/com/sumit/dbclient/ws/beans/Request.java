package com.sumit.dbclient.ws.beans;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Request")
public class Request {

	public Object body;

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
	
	
}
