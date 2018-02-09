package org.zappos.param.tummytruck.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException>{

	@Override
	public Response toResponse(BadRequestException exception) {
		//can add more items as per the need
		System.out.println("BEXCEPTION");
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}
	
}
