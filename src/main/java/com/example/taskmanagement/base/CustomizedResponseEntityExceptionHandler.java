/*package com.example.taskmanagement.base;

import com.example.taskmanagement.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<GenericErrorMessage> handleUsernameNotFoundException(UserNotFoundException e, WebRequest webRequest) {

        String message = e.getErrorMessage();
        String description = webRequest.getDescription(false);

        var genericErrorMessage = new GenericErrorMessage(LocalDateTime.now(), message, description);

        return ResponseEntity.internalServerError().body(genericErrorMessage);
    }

}
*/