package com.wipro.usermgmt.exceptions;

public class ProcessInterruptedException extends FatalException {

	private static final long serialVersionUID = 1L;

	public ProcessInterruptedException(String message) {
		super(message);
	}

}
