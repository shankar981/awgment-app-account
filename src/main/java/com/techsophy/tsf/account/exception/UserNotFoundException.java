package com.techsophy.tsf.account.exception;

public class UserNotFoundException extends RuntimeException
{
    final String errorcode;
    final String message;
    public UserNotFoundException(String errorcode,String message)
    {
        super(message);
        this.errorcode=errorcode;
        this.message=message;
    }
}
