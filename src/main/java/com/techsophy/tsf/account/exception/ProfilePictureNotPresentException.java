package com.techsophy.tsf.account.exception;

public class ProfilePictureNotPresentException extends RuntimeException
{
    final String errorcode;
    final String message;
        public ProfilePictureNotPresentException(String errorcode,String message)
        {
            super(message);
            this.errorcode=errorcode;
            this.message=message;
        }
}
