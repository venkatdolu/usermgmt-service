package com.wipro.usermgmt.exceptions;

public class FatalException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public FatalException(String message) {
		super(message);
	}

}
