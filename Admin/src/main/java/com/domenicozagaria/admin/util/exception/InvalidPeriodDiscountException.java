package com.domenicozagaria.admin.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Invalid Period")
public class InvalidPeriodDiscountException extends RuntimeException {
    public InvalidPeriodDiscountException() {
        super();
    }
}
