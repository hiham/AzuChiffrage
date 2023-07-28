package com.example.chiffrage.exception;

import com.example.chiffrage.dto.Error;
import com.example.chiffrage.dto.FieldError;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;


@RestControllerAdvice
@Validated
@RequestMapping("/error")
public class ControllerExceptionHandler {


    @ExceptionHandler({DataNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Error handleDataNotFound(DataNotFoundException exception) {
        return new Error(HttpStatus.NOT_FOUND.value(),new Date(), HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Error> handleMissingParameterException(MissingServletRequestParameterException e) {
        String paramName = e.getParameterName();
        String errorMessage = "Required parameter '" + paramName + "' is missing.";
        return ResponseEntity.badRequest().body(new Error(HttpStatus.BAD_REQUEST.value(),new Date(),HttpStatus.BAD_REQUEST.name(), errorMessage));
    }

    @ExceptionHandler({MissingPathVariableException.class})
    public ResponseEntity<Error> handleMissingPathVariableException(MissingPathVariableException ex) {
        String variableName = ex.getVariableName();
        String errorMessage = "Required path variable '" + variableName + "' is missing.";
        return ResponseEntity.badRequest().body(new Error(HttpStatus.BAD_REQUEST.value(),new Date(),HttpStatus.BAD_REQUEST.name(), errorMessage));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    ResponseEntity<Error> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String errorMessage = "Request body is missing or could not be read.";
        return ResponseEntity.badRequest().body(new Error(HttpStatus.BAD_REQUEST.value(),new Date(),HttpStatus.BAD_REQUEST.name(), errorMessage));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Error> handleMethodArgumentMismatch(MethodArgumentTypeMismatchException ex) {
        String errorMessage = "Invalid method argument: " + ex.getName() + ". Expected type: " + ex.getParameter().getParameterType().getSimpleName() + " (yyyy-MM-dd).";
        return ResponseEntity.badRequest().body(new Error(HttpStatus.BAD_REQUEST.value(),new Date(),HttpStatus.BAD_REQUEST.name(), errorMessage));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<FieldError> handleConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.badRequest().body(new FieldError(HttpStatus.BAD_REQUEST.value(),new Date(),HttpStatus.BAD_REQUEST.name(), "Not valid due to validation error.",FieldError.toMessage(e.getMessage())));
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Error> handleAccessDeniedException(AccessDeniedException ex) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.badRequest().body(new Error(HttpStatus.FORBIDDEN.value(),new Date(),HttpStatus.FORBIDDEN.name(),"Access denied for user with "+ authentication.getAuthorities().stream().toList() +" privileges."));

    }

}
