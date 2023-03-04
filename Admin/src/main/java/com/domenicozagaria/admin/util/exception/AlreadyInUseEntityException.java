package com.domenicozagaria.admin.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Entity name conflict")
public class AlreadyInUseEntityException extends RuntimeException {
    public AlreadyInUseEntityException() {
        super();
    }
    public AlreadyInUseEntityException(String message) {
        super(message);
    }
}
