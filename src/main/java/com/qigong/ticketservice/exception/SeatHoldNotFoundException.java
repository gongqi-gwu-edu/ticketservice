package com.qigong.ticketservice.exception;

public class SeatHoldNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SeatHoldNotFoundException() {
		super();
	}

	public SeatHoldNotFoundException(String s) {
		super(s);
	}
}
