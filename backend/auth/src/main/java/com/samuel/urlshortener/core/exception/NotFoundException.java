package com.samuel.urlshortener.core.exception;

public class NotFoundException extends DomainException{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }
}
