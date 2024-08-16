package com.example.Taskmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
    }

    @ExceptionHandler({TaskNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(TaskNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler({UserNotTheOwnerException.class})
    public ResponseEntity<Object> handleUserNotTheOwnerException(UserNotTheOwnerException exception) {
        return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body(exception.getMessage());
    }
}