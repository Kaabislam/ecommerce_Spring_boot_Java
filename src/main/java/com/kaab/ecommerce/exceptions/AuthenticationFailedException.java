package com.kaab.ecommerce.exceptions;

public class AuthenticationFailedException extends IllegalArgumentException{
    public AuthenticationFailedException(String msg){
        super(msg);
    }
}
