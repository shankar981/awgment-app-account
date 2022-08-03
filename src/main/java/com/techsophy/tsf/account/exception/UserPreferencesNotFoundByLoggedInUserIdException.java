package com.techsophy.tsf.account.exception;

public class UserPreferencesNotFoundByLoggedInUserIdException extends RuntimeException
{
    final String errorcode;
    final String message;
    public UserPreferencesNotFoundByLoggedInUserIdException(String errorcode,String message)
    {
        super(message);
        this.errorcode=errorcode;
        this.message=message;
    }
}
