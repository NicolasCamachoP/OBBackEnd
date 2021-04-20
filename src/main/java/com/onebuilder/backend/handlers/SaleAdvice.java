package com.onebuilder.backend.handlers;

import com.onebuilder.backend.exception.ClientWithoutSalesException;
import com.onebuilder.backend.exception.SaleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SaleAdvice {

    @ResponseBody
    @ExceptionHandler(SaleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String saleNotFound(SaleNotFoundException e){
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ClientWithoutSalesException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String clientWithoutSales(ClientWithoutSalesException e){
        return e.getMessage();
    }
}
