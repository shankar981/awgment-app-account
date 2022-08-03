package com.techsophy.tsf.account.exception;

public class ClientsUnableToRetrieveException extends RuntimeException
{
    final String errorCode;
    final String message;
    public ClientsUnableToRetrieveException(String errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
        this.message=message;

    }
}
