package com.qigong.ticketservice.service;

import java.sql.Timestamp;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qigong.ticketservice.domain.SeatHold;
import com.qigong.ticketservice.repository.SeatHoldRepository;

@Service
public class SeatHoldServiceImpl implements SeatHoldService {
    private SeatHoldRepository seatHoldRepository;

    @Value("${seathold.expiretime.insecond}")
    private int seatHoldExpireTime;

    @Autowired
    public void setSeatHoldRepository(SeatHoldRepository seatHoldRepository) {
        this.seatHoldRepository = seatHoldRepository;
    }

    @Override
    public Iterable<SeatHold> listAllSeatHolds() {
    	//Remove Expired SeatHolds
        seatHoldRepository.delete(seatHoldRepository.findByReservationTimeIsNullAndHoldTimeBefore(Timestamp.from(Instant.now().minusSeconds(seatHoldExpireTime))));
        return seatHoldRepository.findAll();
    }

    @Override
    public SeatHold getSeatHoldBySeatHoldId(Integer seatHoldId) {
        return seatHoldRepository.findOne(seatHoldId);
    }

    @Override
    public void deleteSeatHold(Integer seatHoldId) {
        seatHoldRepository.delete(seatHoldId);
    }
}
