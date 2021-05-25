package com.onebuilder.backend.exception;

public class SaleNotFoundException extends RuntimeException {
    public SaleNotFoundException(Long id) {
        super("Could not find Sale: " + id);
    }
}
