package org.zappos.param.tummytruck.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException exception) {
		System.out.println("DEXCEPTION");
		return Response.status(Status.NOT_FOUND).entity(exception.getMessage()).build();
	}
}
