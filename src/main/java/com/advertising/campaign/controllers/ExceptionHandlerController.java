package com.advertising.campaign.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

public class ExceptionHandlerController {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException(IllegalArgumentException exception) {
        return exception.getMessage();
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException(NullPointerException exception) {
        return "Set parameter nameFilter";
    }

    @ExceptionHandler(ClassNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException(ClassNotFoundException exception) {
        return "Set parameter nameFilter";
    }

    @ExceptionHandler(SQLException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException(SQLException exception) {
        return "Set parameter nameFilter";
    }

    @ExceptionHandler(InstantiationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException(InstantiationException exception) {
        return "Set parameter nameFilter";
    }
}
