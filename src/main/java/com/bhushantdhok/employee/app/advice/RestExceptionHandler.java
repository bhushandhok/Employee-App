package com.bhushantdhok.employee.app.advice;

import com.bhushantdhok.employee.app.exceptions.EmployeeNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EmployeeNotFoundException.class})
    public ResponseEntity<?> handleDuplicateKeyException(EmployeeNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                Collections.singletonMap("message", ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request
        );
    }

    @ExceptionHandler({HttpClientErrorException.BadRequest.class}) public ResponseEntity<?> handleDuplicateKeyException(HttpClientErrorException.BadRequest ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                Collections.singletonMap("message", ex.getMessage()),
                new HttpHeaders(),
                HttpStatus.CONFLICT,
                request
        );
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleBindException(new BindException(ex.getBindingResult()), headers, status, request);
    }

}
