package com.qigong.ticketservice.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.qigong.ticketservice.domain.Level;
import com.qigong.ticketservice.domain.SeatHold;
import com.qigong.ticketservice.domain.SeatHoldDetail;
import com.qigong.ticketservice.exception.EmailValidationException;
import com.qigong.ticketservice.exception.SeatHoldNumberValidationException;
import com.qigong.ticketservice.exception.LevelValidationException;
import com.qigong.ticketservice.exception.SeatHoldExpiredException;
import com.qigong.ticketservice.exception.SeatHoldNotFoundException;
import com.qigong.ticketservice.repository.LevelRepository;
import com.qigong.ticketservice.repository.SeatHoldDetailRepository;
import com.qigong.ticketservice.repository.SeatHoldRepository;

@Service
@Transactional(value="transactionManager", isolation= Isolation.SERIALIZABLE)
public class TicketServiceImpl implements TicketService {
    private LevelRepository levelRepository;
    private SeatHoldRepository seatHoldRepository;
    private SeatHoldDetailRepository seatHoldDetailRepository;

    @Value("${seathold.expiretime.insecond}")
    private int seatHoldExpireTime;

    @Autowired
    public void setLevelRepository(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Autowired
    public void setSeatHoldRepository(SeatHoldRepository seatHoldRepository) {
        this.seatHoldRepository = seatHoldRepository;
    }

    @Autowired
    public void setSeatHoldDetailRepository(SeatHoldDetailRepository seatHoldDetailRepository) {
        this.seatHoldDetailRepository = seatHoldDetailRepository;
    }

    @Override
    public int numSeatsAvailable(Optional<Integer> venueLevel) {
        //Remove Expired SeatHolds
        seatHoldRepository.delete(seatHoldRepository.findByReservationTimeIsNullAndHoldTimeBefore(Timestamp.from(Instant.now().minusSeconds(seatHoldExpireTime))));

        int allSeatsNum = 0;
        int holdedSeatsNum = 0;
        if (venueLevel.isPresent()) {
            Level level = levelRepository.findOne(venueLevel.get());
            allSeatsNum = level.getRowNumber() * level.getSeatNumberInRow();
            for (SeatHoldDetail seatHoldDetail : seatHoldDetailRepository.findByLevel(level)) {
                holdedSeatsNum += seatHoldDetail.getSeatNumber();
            }
        } else {
            for (Level level : levelRepository.findAll()) {
                allSeatsNum += level.getRowNumber() * level.getSeatNumberInRow();
            }
            for (SeatHoldDetail seatHoldDetail : seatHoldDetailRepository.findAll()) {
                holdedSeatsNum += seatHoldDetail.getSeatNumber();
            }
        }
        return allSeatsNum - holdedSeatsNum;
    }

    @Override
    public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) {
        if (numSeats < 1) {
            throw new SeatHoldNumberValidationException("Hold Seats Number is less than 1");
        } else if (null == customerEmail || "".equals(customerEmail) || !Pattern.matches("^[a-z_0-9.-]{1,64}@([a-z0-9-]{1,200}.){1,5}[a-z]{1,6}$", customerEmail)) {
            throw new EmailValidationException("Email is invalid");
        } else if (minLevel.isPresent() && maxLevel.isPresent() && minLevel.get() > maxLevel.get()) {
            throw new LevelValidationException("minLevel is greater than maxLevel");
        }
        Integer minLevelId = levelRepository.findMinLevelId().getLevelId();
        Integer maxLevelId = levelRepository.findMaxLevelId().getLevelId();
        if (minLevel.isPresent() && (minLevel.get() < minLevelId || minLevel.get() > maxLevelId)) {
            throw new LevelValidationException("minLevel is not in valid range");
        } else if (maxLevel.isPresent() && (maxLevel.get() < minLevelId || maxLevel.get() > maxLevelId)) {
            throw new LevelValidationException("maxLevel is not in valid range");
        }

        Iterable<Level> levels = levelRepository.findByLevelIdBetween(maxLevel.isPresent() ? maxLevel.get() : maxLevelId, 
                                                                      minLevel.isPresent() ? minLevel.get() : minLevelId);

        SeatHold seatHold = new SeatHold();
        seatHold.setCustomerEmail(customerEmail);
        int availableSeatsNum;
        for (Level level : levels) {
            if (numSeats > 0) {
                availableSeatsNum = numSeatsAvailable(Optional.of(level.getLevelId()));
                if (availableSeatsNum == 0) {
                    continue;
                } else if (numSeats > availableSeatsNum) {
                    seatHold.getSeatHoldDetails().add(new SeatHoldDetail(seatHold, level, availableSeatsNum));
                    numSeats -= availableSeatsNum;
                } else {
                    seatHold.getSeatHoldDetails().add(new SeatHoldDetail(seatHold, level, numSeats));
                    numSeats = 0;
                }
            } else {
                break;
            }
        }
        if (numSeats == 0) {
            seatHold.setHoldTime(Timestamp.from(Instant.now()));
            return seatHoldRepository.save(seatHold);
        } else {
            return null;
        }
    }

    @Override
    public String reserveSeats(int seatHoldId, String customerEmail) {
        SeatHold seatHold = seatHoldRepository.findOne(seatHoldId);
        if (null == seatHold) {
            throw new SeatHoldNotFoundException("No Seat Hold is found. Seat Hold may expired");
        } else if (null == customerEmail || !seatHold.getCustomerEmail().equalsIgnoreCase(customerEmail)) {
            throw new EmailValidationException("Email is incorrect");
        } else if (null != seatHold.getConfirmationCode() && !"".equals(seatHold.getConfirmationCode())) {
            return seatHold.getConfirmationCode();
        } else if (seatHold.getHoldTime().before(Timestamp.from(Instant.now().minusSeconds(seatHoldExpireTime)))) {
            throw new SeatHoldExpiredException("Seat Hold expired");
        } else {
            seatHold.setConfirmationCode(UUID.randomUUID().toString());
            return seatHoldRepository.save(seatHold).getConfirmationCode();
        }
    }
}
