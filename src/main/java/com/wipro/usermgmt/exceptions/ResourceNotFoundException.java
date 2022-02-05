package com.wipro.usermgmt.exceptions;

public class ResourceNotFoundException extends FatalException {
	
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}


}
