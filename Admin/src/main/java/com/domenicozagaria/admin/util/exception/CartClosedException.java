package com.domenicozagaria.admin.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Cart already closed")
public class CartClosedException extends RuntimeException {
    public CartClosedException() {
        super();
    }
}
