package com.qigong.ticketservice.exception;

import javax.validation.ValidationException;

public class LevelValidationException extends ValidationException {

    private static final long serialVersionUID = 1L;

    public LevelValidationException() {
        super();
    }

    public LevelValidationException(String s) {
        super(s);
    }
}
