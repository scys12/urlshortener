package com.samuel.urlshortener.core.exception;

public abstract class DomainException extends RuntimeException{
    public DomainException(String message){
        super(message);
    }
}
