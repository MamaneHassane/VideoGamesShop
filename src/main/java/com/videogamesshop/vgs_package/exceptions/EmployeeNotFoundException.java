package com.videogamesshop.vgs_package.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super("Employee with id "+id+ " was not found");
    }
}
