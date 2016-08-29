package com.qigong.ticketservice.repository;

import java.sql.Timestamp;

import org.springframework.data.repository.CrudRepository;

import com.qigong.ticketservice.domain.SeatHold;

public interface SeatHoldRepository extends CrudRepository<SeatHold, Integer> {
	Iterable<SeatHold> findByReservationTimeIsNullAndHoldTimeBefore(Timestamp timestamp);
}
