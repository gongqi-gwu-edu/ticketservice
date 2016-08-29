package com.qigong.ticketservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.qigong.ticketservice.domain.Level;
import com.qigong.ticketservice.domain.SeatHoldDetail;

public interface SeatHoldDetailRepository extends CrudRepository<SeatHoldDetail, Integer> {
    Iterable<SeatHoldDetail> findByLevel(Level level);
}
