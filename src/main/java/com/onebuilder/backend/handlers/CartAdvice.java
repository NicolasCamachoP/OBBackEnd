package com.onebuilder.backend.handlers;
import com.onebuilder.backend.exception.CartNotFoundException;

import com.onebuilder.backend.exception.StockNotEnoughException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CartAdvice {
    @ResponseBody
    @ExceptionHandler(CartNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cartNotFound(CartNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(StockNotEnoughException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    String notEnoughStock(StockNotEnoughException e) {
        return e.getMessage();
    }
}
