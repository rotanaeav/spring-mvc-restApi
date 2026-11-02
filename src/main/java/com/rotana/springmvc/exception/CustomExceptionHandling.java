package com.rotana.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandling {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleException(MethodArgumentNotValidException e) {
        List<FieldErrorResponse> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(
                        FieldErrorResponse.builder()
                                .field(error.getField())
                                .message(error.getDefaultMessage())
                                .build()
                ));

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(Boolean.FALSE)
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Logic Validation Failed")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
        return errorResponse;
    }
}
