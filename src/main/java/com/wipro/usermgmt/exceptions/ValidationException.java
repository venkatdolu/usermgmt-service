package com.wipro.usermgmt.exceptions;

public class ValidationException extends FatalException
{
	private static final long serialVersionUID = 1L;

	public ValidationException( String message )
    {
        super( message );
    }
}
