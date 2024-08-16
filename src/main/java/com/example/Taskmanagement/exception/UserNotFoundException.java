package com.example.Taskmanagement.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
        System.out.print("in user exception");
    }
}