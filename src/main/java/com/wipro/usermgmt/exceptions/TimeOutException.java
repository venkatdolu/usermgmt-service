package com.wipro.usermgmt.exceptions;

public class TimeOutException extends FatalException {

	private static final long serialVersionUID = 1L;
	
	public TimeOutException(String message) {
		super(message);
	}

}
