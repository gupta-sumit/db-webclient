package com.sumit.dbclient.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sumit.dbclient.ws.beans.Request;
import com.sumit.dbclient.ws.beans.Response;

@Path("/config/v1.0")
public interface DatabaseConfigurationService {

	@POST
	@Path("/save")
	@Consumes({"application/xml", "application/json", "application/x-java-serialized-object"})
	@Produces({"application/xml", "application/json","application/x-java-serialized-object"})
	public Response saveDBConfiguration(Request request);
	
	
	@GET
	@Path("/fetchAll")
	@Consumes({"application/xml", "application/json", "application/x-java-serialized-object"})
	@Produces({"application/xml", "application/json","application/x-java-serialized-object"})
	public Response getAllConfigurations();
	
	
}
