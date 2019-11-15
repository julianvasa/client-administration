package com.client.administration.exceptions;

import com.client.administration.exceptions.dto.ValidationErrorResponse;
import com.client.administration.exceptions.dto.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    /* Exception handler for bean validation exceptions */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ValidationErrorResponse onConstraintValidationException(
        ConstraintViolationException e) {
        ValidationErrorResponse errors = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            errors.getViolations().add(Violation.builder().dateStamp(new Date().toString()).field(violation.getPropertyPath().toString()).message(violation.getMessage()).build());
        }
        return errors;
    }

    /* Exception handler for all other exceptions*/
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ValidationErrorResponse errors = new ValidationErrorResponse();
        errors.getViolations().add(Violation.builder().dateStamp(new Date().toString()).message(ex.getMessage()).build());
        ex.printStackTrace();
        return new ResponseEntity<>(errors, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
