package com.techsophy.tsf.account.exception;

public class EntityIdNotFoundException extends RuntimeException
{
    final String message;
    final String errorcode;

    public EntityIdNotFoundException(String errorcode,String message)
    {
        super(message);
        this.errorcode=errorcode;
        this.message=message;
    }
}
