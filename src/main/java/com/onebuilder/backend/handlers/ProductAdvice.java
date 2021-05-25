package com.onebuilder.backend.handlers;

import com.onebuilder.backend.exception.ProductAlreadyRegisteredException;
import com.onebuilder.backend.exception.ProductNotFoundException;
import com.onebuilder.backend.exception.ProductNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductAdvice {

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String productNotFound(ProductNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProductAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String productAlreadyRegistered(ProductAlreadyRegisteredException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProductNotValidException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    String productNotValid(ProductNotValidException e) {
        return e.getMessage();
    }
}
