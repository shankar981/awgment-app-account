package com.techsophy.tsf.account.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RolesNotFoundException extends RuntimeException
{
    final String errorcode;
    final String message;
    public RolesNotFoundException(String errorCode, String message)
    {
        super(message);
        this.errorcode = errorCode;
        this.message=message;
    }
}
