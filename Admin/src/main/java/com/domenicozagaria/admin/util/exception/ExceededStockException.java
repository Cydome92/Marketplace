package com.domenicozagaria.admin.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "No Stock Present")
public class ExceededStockException extends RuntimeException {

    public ExceededStockException() {
        super();
    }

    public ExceededStockException(String message) {
        super(message);
    }
}
