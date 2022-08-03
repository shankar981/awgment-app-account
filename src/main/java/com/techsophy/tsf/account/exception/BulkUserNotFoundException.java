package com.techsophy.tsf.account.exception;

public class BulkUserNotFoundException extends RuntimeException
{
    final String errorCode;
    final String message;
    public BulkUserNotFoundException(String errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
        this.message=message;

    }
}
