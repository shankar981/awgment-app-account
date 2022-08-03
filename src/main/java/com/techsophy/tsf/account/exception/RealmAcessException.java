package com.techsophy.tsf.account.exception;

public class RealmAcessException extends RuntimeException
{
    final String errorcode;
    final String message;
    public RealmAcessException(String errorCode,String message)
    {
        super(errorCode);
        this.errorcode=errorCode;
        this.message=message;
    }
}
