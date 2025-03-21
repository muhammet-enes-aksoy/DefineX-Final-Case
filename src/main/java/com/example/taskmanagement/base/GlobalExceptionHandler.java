/*package com.example.taskmanagement.base;

import com.example.taskmanagement.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<RestResponse<String>> handleProjectNotFound(ProjectNotFoundException ex) {
        return new ResponseEntity<>(RestResponse.error(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<RestResponse<String>> handleTaskNotFound(TaskNotFoundException ex) {
        return new ResponseEntity<>(RestResponse.error(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<RestResponse<String>> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(RestResponse.error(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidTaskStateException.class)
    public ResponseEntity<RestResponse<String>> handleInvalidTaskState(InvalidTaskStateException ex) {
        return new ResponseEntity<>(RestResponse.error(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RestResponse<String>> handleIllegalArgument(IllegalArgumentException ex) {
        return new ResponseEntity<>(RestResponse.error(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse<String>> handleGeneric(Exception ex) {
        return new ResponseEntity<>(RestResponse.error("An unexpected error occurred: " + ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
*/