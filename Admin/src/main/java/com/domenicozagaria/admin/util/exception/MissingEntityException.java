package com.domenicozagaria.admin.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Entity not found")
public class MissingEntityException extends RuntimeException {
    public MissingEntityException() {
        super();
    }

    public MissingEntityException(String message) {
        super(message);
    }
}
