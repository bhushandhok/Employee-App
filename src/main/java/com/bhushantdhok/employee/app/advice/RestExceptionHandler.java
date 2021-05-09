package com.bhushantdhok.employee.app.advice;

import com.bhushantdhok.employee.app.error.ValidationError;
import com.bhushantdhok.employee.app.exceptions.EmployeeNotFoundException;
import com.bhushantdhok.employee.app.service.IMessageService;
import com.bhushantdhok.employee.app.utils.ValidationExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    IMessageService messageService;
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

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleValidation(ex, ex.getBindingResult(), request);
    }
    private ResponseEntity<Object>  handleValidation(Exception ex, BindingResult result, WebRequest request) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        ValidationError mainDto = ValidationExceptionUtil.processFieldErrors(messageService, fieldErrors);
        List<ObjectError> objectErrors = result.getGlobalErrors();
        List<String> starErrors = ValidationExceptionUtil.processGlobalErrors(messageService, objectErrors);
        for (String starError : starErrors) {
            mainDto.addError("*", starError);
        }

        return handleExceptionInternal(
                ex,
                Collections.singletonMap("hints", mainDto),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }
}
