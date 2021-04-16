package com.onebuilder.backend.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String EAN){
        super("Could not find Product: " + EAN);
    }
}

