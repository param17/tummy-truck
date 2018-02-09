package org.zappos.param.tummytruck.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	Logger logger = Logger.getLogger(GenericExceptionMapper.class.getName());
	
	public Response toResponse(Throwable exp) {
		logger.error(exp.getStackTrace());
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(exp.getMessage()).build();
	}
	
}
