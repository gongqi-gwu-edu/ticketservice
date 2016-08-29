package com.qigong.ticketservice.exception;

import javax.validation.ValidationException;

public class SeatHoldNumberValidationException extends ValidationException {

	private static final long serialVersionUID = 1L;

	public SeatHoldNumberValidationException() {
		super();
	}

	public SeatHoldNumberValidationException(String s) {
		super(s);
	}
}
