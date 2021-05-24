package com.onebuilder.backend.exception;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(String email){
        super("Could not find Cart from: " + email);
    }
}
