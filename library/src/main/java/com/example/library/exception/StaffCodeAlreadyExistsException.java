package com.example.library.exception;


public class StaffCodeAlreadyExistsException extends RuntimeException {

    public StaffCodeAlreadyExistsException(String message) {
        super(message);
    }
}