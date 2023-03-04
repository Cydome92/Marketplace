package com.domenicozagaria.admin.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "Invalid percentage value")
public class InvalidPercentageException extends RuntimeException {
    public InvalidPercentageException() {
        super();
    }
}
