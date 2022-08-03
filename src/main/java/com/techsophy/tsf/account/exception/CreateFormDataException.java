package com.techsophy.tsf.account.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateFormDataException extends RuntimeException
{
    final String errorcode;
    final String message;
    public CreateFormDataException(String errorCode, String message)
    {
        super(message);
        this.errorcode=errorCode;
        this.message=message;
    }
}
