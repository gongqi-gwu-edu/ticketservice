package com.qigong.ticketservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qigong.ticketservice.domain.SeatHold;
import com.qigong.ticketservice.repository.SeatHoldRepository;

@Service
public class SeatHoldServiceImpl implements SeatHoldService {
    private SeatHoldRepository seatHoldRepository;

    @Autowired
    public void setSeatHoldRepository(SeatHoldRepository seatHoldRepository) {
        this.seatHoldRepository = seatHoldRepository;
    }

    @Override
    public Iterable<SeatHold> listAllSeatHolds() {
        return seatHoldRepository.findAll();
    }

    @Override
    public SeatHold getSeatHoldBySeatHoldId(Integer seatHoldId) {
        return seatHoldRepository.findOne(seatHoldId);
    }

    @Override
    public SeatHold saveSeatHold(SeatHold seatHold) {
        return seatHoldRepository.save(seatHold);
    }

    @Override
    public void deleteSeatHold(Integer seatHoldId) {
        seatHoldRepository.delete(seatHoldId);
    }
}
