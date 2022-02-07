package com.wipro.usermgmt.exceptions;

@SuppressWarnings("serial")
public class DataNotFoundException extends FatalException
{
    public DataNotFoundException( String message )
    {
        super( message );
    }
}
