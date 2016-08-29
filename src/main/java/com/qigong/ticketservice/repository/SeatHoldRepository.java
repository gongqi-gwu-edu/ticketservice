package com.qigong.ticketservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.qigong.ticketservice.domain.SeatHold;

public interface SeatHoldRepository extends CrudRepository<SeatHold, Integer> {
	Iterable<SeatHold> findByReservationTimeIsNullAndHoldTimeBefore() {
		
	}
}
