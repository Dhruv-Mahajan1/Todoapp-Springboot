package com.example.Taskmanagement.exception;

public class UserNotTheOwnerException extends RuntimeException {
    public UserNotTheOwnerException(String message) {
        super(message);
    }
}
