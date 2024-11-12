package com.example.oulam.lets_play.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidationException extends RuntimeException {

    private final HttpStatus httpStatus;

    private final Map<String, String> errors;

    public ValidationException(HttpStatus httpStatus, Map<String, String> errors) {
        super("Validation error");
        this.httpStatus = httpStatus;
        this.errors = errors;
    }

    public ValidationException(String field, String message) {
        this(HttpStatus.BAD_REQUEST, new HashMap<>() {{ put(field, message); }});
    }
}
