package com.onebuilder.backend.exception;

public class WrongUserCredentialsException extends RuntimeException {
    public WrongUserCredentialsException() {
        super("No matching result for given email or password");
    }
}
