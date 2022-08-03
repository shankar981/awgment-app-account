package com.techsophy.tsf.account.exception;

public class ThemesNotFoundByIdException extends RuntimeException
{
    final String errorCode;
    final String message;
    public  ThemesNotFoundByIdException(String errorCode, String message)
    {
        super(message);
        this.errorCode = errorCode;
        this.message=message;
    }
}
