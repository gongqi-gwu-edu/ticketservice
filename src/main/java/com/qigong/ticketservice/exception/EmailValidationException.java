package com.qigong.ticketservice.exception;

import javax.validation.ValidationException;

public class EmailValidationException extends ValidationException {

	private static final long serialVersionUID = 1L;

	public EmailValidationException() {
		super();
	}

	public EmailValidationException(String s) {
		super(s);
	}
}
