package com.qigong.ticketservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qigong.ticketservice.domain.SeatHoldDetail;
import com.qigong.ticketservice.repository.SeatHoldDetailRepository;

@Service
public class SeatHoldDetailServiceImpl implements SeatHoldDetailService {
    private SeatHoldDetailRepository seatHoldDetailRepository;

    @Autowired
    public void setSeatHoldDetailRepository(SeatHoldDetailRepository seatHoldDetailRepository) {
        this.seatHoldDetailRepository = seatHoldDetailRepository;
    }

    @Override
    public Iterable<SeatHoldDetail> listAllSeatHoldDetails() {
        return seatHoldDetailRepository.findAll();
    }

    @Override
    public SeatHoldDetail getSeatHoldDetailById(Integer id) {
        return seatHoldDetailRepository.findOne(id);
    }

    @Override
    public SeatHoldDetail saveSeatHoldDetail(SeatHoldDetail seatHoldDetail) {
        return seatHoldDetailRepository.save(seatHoldDetail);
    }

    @Override
    public void deleteSeatHoldDetail(Integer id) {
        seatHoldDetailRepository.delete(id);
    }
}
