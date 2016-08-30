package com.qigong.ticketservice.service;

import com.qigong.ticketservice.domain.SeatHoldDetail;

public interface SeatHoldDetailService {
    Iterable<SeatHoldDetail> listAllSeatHoldDetails();

    SeatHoldDetail getSeatHoldDetailById(Integer id);
}
