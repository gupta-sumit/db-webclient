package com.sumit.dbclient.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.sumit.dbclient.ws.beans.Response;

@Path("/metadata/v1.0")
public interface DatabaseMetaDataService {

	@GET
	@Path("/fetchTables")
	@Consumes({"application/xml", "application/json", "application/x-java-serialized-object"})
	@Produces({"application/xml", "application/json","application/x-java-serialized-object"})
	public Response getTables(@QueryParam("configKey") String databaseConfigKey);
	
	@GET
	@Path("/fetchColumns")
	@Consumes({"application/xml", "application/json", "application/x-java-serialized-object"})
	@Produces({"application/xml", "application/json","application/x-java-serialized-object"})
	public Response getTableColumns(@QueryParam("configKey") String databaseConfigKey, @QueryParam("tab") String tableName);
	
	
	
}
