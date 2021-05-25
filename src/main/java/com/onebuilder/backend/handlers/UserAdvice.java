package com.onebuilder.backend.handlers;

import com.onebuilder.backend.exception.EmailAlreadyTakenException;
import com.onebuilder.backend.exception.NotValidUserException;
import com.onebuilder.backend.exception.UserNotFoundException;
import com.onebuilder.backend.exception.WrongUserCredentialsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String userNotFoundHandler(UserNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(EmailAlreadyTakenException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String userEmailAlreadyTaken(UserNotFoundException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(WrongUserCredentialsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String wrongUserCredentials(WrongUserCredentialsException e) {
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(NotValidUserException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String notValidUser(NotValidUserException e) {
        return e.getMessage();
    }
}
