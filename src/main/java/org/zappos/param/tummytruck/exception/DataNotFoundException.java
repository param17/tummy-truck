package org.zappos.param.tummytruck.exception;

public class DataNotFoundException extends RuntimeException {
	
	//Can create similar exceptions for different scenarios	
	public DataNotFoundException(String error_msg) {
		super(error_msg);
	}
	
}
