package com.inderjeet.springBootDemo.exception;

public class NotAuthorizedException extends RuntimeException{

    String message;

    public NotAuthorizedException(String message){
        super(message);
        this.message = message;

    }

}
