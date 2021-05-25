package com.onebuilder.backend.exception;

public class ProductAlreadyRegisteredException extends RuntimeException {
    public ProductAlreadyRegisteredException(String message) {
        super(message);
    }
}
