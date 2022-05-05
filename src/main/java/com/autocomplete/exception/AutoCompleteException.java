package com.autocomplete.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AutoCompleteException extends ResponseStatusException
{
    private static final long serialVersionUID = 1L;


    public AutoCompleteException(String message, HttpStatus httpStatus) {
        super(httpStatus, message);

    }

}