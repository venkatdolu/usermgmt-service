package com.wipro.usermgmt.exceptions;

public class NoResultsFoundException extends FatalException {

	private static final long serialVersionUID = 1L;

	public NoResultsFoundException(String message) {
		super(message);
	}

}
