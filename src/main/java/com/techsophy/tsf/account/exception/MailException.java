package com.techsophy.tsf.account.exception;

public class MailException extends RuntimeException
{
    final String errorcode;
    final String message;
    public MailException(String errorCode,String message)
    {
        super(errorCode);
        this.errorcode=errorCode;
        this.message=message;
    }
}
