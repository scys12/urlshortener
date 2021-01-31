package com.samuel.urlshortener.core.exception;

public class EmailExistException extends DomainException {
    public EmailExistException(String message) {
        super(message);
    }
}
