package com.qigong.ticketservice.exception;

public class SeatHoldExpiredException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SeatHoldExpiredException() {
        super();
    }

    public SeatHoldExpiredException(String s) {
        super(s);
    }
}
