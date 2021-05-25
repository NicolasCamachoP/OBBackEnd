package com.onebuilder.backend.exception;

public class ClientWithoutSalesException extends RuntimeException {
    public ClientWithoutSalesException(Long clientID) {
        super("No matching Sales for client: " + clientID);
    }
}
