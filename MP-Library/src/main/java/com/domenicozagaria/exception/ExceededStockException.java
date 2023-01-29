package com.domenicozagaria.exception;

public class ExceededStockException extends RuntimeException {

    public ExceededStockException() {
        super();
    }

    public ExceededStockException(String message) {
        super(message);
    }
}
