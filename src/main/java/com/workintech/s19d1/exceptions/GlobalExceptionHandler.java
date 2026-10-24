package com.workintech.s19d1.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Tüm hatalar tek merkezden yönetilir; controller'larda try/catch yoktur.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionResponse> handleApiException(ApiException ex) {
        log.error("ApiException: {}", ex.getMessage());
        return build(ex.getMessage(), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        return build(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ExceptionResponse> build(String message, HttpStatus status) {
        ExceptionResponse response = new ExceptionResponse(message, status.value(), LocalDateTime.now());
        return new ResponseEntity<>(response, status);
    }
}
