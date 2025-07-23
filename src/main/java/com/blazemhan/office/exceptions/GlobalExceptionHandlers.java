package com.blazemhan.office.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<String> handleException(RequestNotFoundException ex) {
    return ResponseEntity.badRequest().body(ex.getMessage());

    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(RejectionException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());

    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(ApprovalException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());

    }
    @ExceptionHandler
    public ResponseEntity<String> handleException(ReleaseException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());

    }
}
