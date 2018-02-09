package org.zappos.param.tummytruck.exception;

public class BadRequestException extends RuntimeException {

	public BadRequestException(String exp_msg) {
		super(exp_msg);
	}
	
}
