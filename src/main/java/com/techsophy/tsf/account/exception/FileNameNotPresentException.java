package com.techsophy.tsf.account.exception;

public class FileNameNotPresentException extends  RuntimeException
{
    final String errorcode;
    final String message;
    public FileNameNotPresentException(String errorcode,String message)
    {
        super(message);
        this.errorcode=errorcode;
        this.message=message;
    }
}
