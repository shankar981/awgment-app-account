package com.techsophy.tsf.account.exception;

public class ServiceNotEnabledException extends RuntimeException
{
    final String errorcode;
    final String message;
    public ServiceNotEnabledException(String errorcode,String message)
    {
        super(message);
        this.errorcode=errorcode;
        this.message=message;
    }


}
