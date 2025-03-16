package com.example.taskmanagement.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("UserNotFoundException");
    }
}
