package com.techsophy.tsf.account.exception;

public class InvalidProfilePictureException extends  RuntimeException
{
    final String errorcode;
    final String message;
    public  InvalidProfilePictureException(String errorcode,String message)
    {
        super(message);
        this.errorcode=errorcode;
        this.message=message;
    }
}
