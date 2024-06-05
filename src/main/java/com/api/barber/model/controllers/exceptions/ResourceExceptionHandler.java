package com.api.barber.model.controllers.exceptions;

import com.api.barber.model.services.exceptions.DatabaseException;
import com.api.barber.model.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(Instant.now(), HttpStatus.NOT_FOUND.value(), "Not found Error", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        StandardError err = new StandardError(Instant.now(), HttpStatus.BAD_REQUEST.value(), "Database Bad Request", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StandardError> internetError(RuntimeException e, HttpServletRequest request) {
        StandardError err = new StandardError(Instant.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internet Server Error", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
