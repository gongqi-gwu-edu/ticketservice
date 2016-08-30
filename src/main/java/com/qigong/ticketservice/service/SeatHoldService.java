package com.qigong.ticketservice.service;

import com.qigong.ticketservice.domain.SeatHold;

public interface SeatHoldService {
    Iterable<SeatHold> listAllSeatHolds();

    SeatHold getSeatHoldBySeatHoldId(Integer seatHoldId);

    void deleteSeatHold(Integer seatHoldId);
}
