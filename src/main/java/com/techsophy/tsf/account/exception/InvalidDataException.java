package com.techsophy.tsf.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidDataException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	final String errorcode;
	final String message;
	public InvalidDataException(String errorcode,String message)
	{
		super(message);
		this.errorcode=errorcode;
		this.message=message;
	}
}
