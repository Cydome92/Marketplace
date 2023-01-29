package com.domenicozagaria.exception;

public class AlreadyInUseEntityException extends RuntimeException {
    public AlreadyInUseEntityException() {
        super();
    }
    public AlreadyInUseEntityException(String message) {
        super(message);
    }
}
