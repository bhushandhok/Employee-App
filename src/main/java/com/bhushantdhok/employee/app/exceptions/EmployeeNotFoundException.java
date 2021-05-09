package com.bhushantdhok.employee.app.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String s) {
        super(s);
    }
}
