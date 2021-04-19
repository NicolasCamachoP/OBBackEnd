package com.onebuilder.backend.exception;

public class EmailAlreadyTakenException extends RuntimeException{
    public EmailAlreadyTakenException(String email){ super("Email: " + email + " already taken..."); }
}
