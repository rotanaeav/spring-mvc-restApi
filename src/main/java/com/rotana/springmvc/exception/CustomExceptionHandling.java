package com.rotana.springmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CustomExceptionHandling {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException e) {
        return ErrorResponse.builder()
                .status(Boolean.FALSE)
                .code(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .errors(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConflictException(ConflictException e) {
        return ErrorResponse.builder()
                .status(Boolean.FALSE)
                .code(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .errors(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponse handleResponseStatusException(ResponseStatusException e) {
        return ErrorResponse.builder()
                .status(Boolean.FALSE)
                .code(e.getStatusCode().value())
                .message(e.getReason())
                .errors(null)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentNotValidException e) {
        List<FieldErrorResponse> errors = new ArrayList<>();

        e.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(
                        FieldErrorResponse.builder()
                                .field(error.getField())
                                .message(error.getDefaultMessage())
                                .build()
                ));

        return ErrorResponse.builder()
                .status(Boolean.FALSE)
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Logic Validation Failed")
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .build();
    }
}
