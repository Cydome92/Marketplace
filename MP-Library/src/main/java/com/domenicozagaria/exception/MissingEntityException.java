package com.domenicozagaria.exception;

public class MissingEntityException extends RuntimeException {
    public MissingEntityException() {
        super();
    }

    public MissingEntityException(String message) {
        super(message);
    }
}
