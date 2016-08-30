package com.qigong.ticketservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qigong.ticketservice.configuration.RepositoryConfiguration;
import com.qigong.ticketservice.domain.Level;
import com.qigong.ticketservice.domain.SeatHold;
import com.qigong.ticketservice.domain.SeatHoldDetail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class SeatHoldRepositoryTest {

	private LevelRepository levelRepository;
    private SeatHoldRepository seatHoldRepository;

    @Autowired
    public void setLevelRepository(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Autowired
    public void setSeatHoldRepository(SeatHoldRepository seatHoldRepository) {
        this.seatHoldRepository = seatHoldRepository;
    }

    @Test
    public void test() {
    	//New
    	SeatHold seatHold = new SeatHold();
        seatHold.setSeatNumber(1);
        seatHold.setMinLevelId(1);
        seatHold.setMaxLevelId(4);
        seatHold.setCustomerEmail("test@test.org");

    	String uuid = UUID.randomUUID().toString();
    	assertNotNull(uuid);
    	seatHold.setConfirmationCode(uuid);

    	Timestamp timestamp = Timestamp.from(Instant.now());
    	assertNotNull(timestamp);
    	seatHold.setHoldTime(timestamp);

        Level level = new Level();
    	level.setLevelName("Orchestra");
    	level.setPrice(new BigDecimal("100.00"));
        level.setRowNumber(25);
        level.setSeatNumberInRow(50);
        level = levelRepository.save(level);
        seatHold.getSeatHoldDetails().add(new SeatHoldDetail(level, 1));

        seatHold.setHoldTime(Timestamp.from(Instant.now()));

        assertNull(seatHold.getSeatHoldId());
        seatHold = seatHoldRepository.save(seatHold);
        assertNotNull(seatHold.getSeatHoldId());

        //Find
        SeatHold fetchedSeatHold = seatHoldRepository.findOne(seatHold.getSeatHoldId());

        assertNotNull(fetchedSeatHold);
        assertEquals(seatHold.getSeatHoldId(), fetchedSeatHold.getSeatHoldId(), 0);
        assertEquals(seatHold.getSeatNumber(), fetchedSeatHold.getSeatNumber(), 0);
        assertEquals(seatHold.getMinLevelId(), fetchedSeatHold.getMinLevelId(), 0);
        assertEquals(seatHold.getMaxLevelId(), fetchedSeatHold.getMaxLevelId(), 0);
        assertEquals(seatHold.getCustomerEmail(), fetchedSeatHold.getCustomerEmail());
        assertEquals(seatHold.getConfirmationCode(), fetchedSeatHold.getConfirmationCode());
        assertEquals(seatHold.getHoldTime(), fetchedSeatHold.getHoldTime());
        assertEquals(seatHold.getReservationTime(), fetchedSeatHold.getReservationTime());

        //Find All
        Iterable<SeatHold> seatHolds = seatHoldRepository.findAll();
        int count = 0;
        for (SeatHold temp : seatHolds) {
            count++;
            assertNotNull(temp);
            assertEquals(temp.getSeatHoldId(), fetchedSeatHold.getSeatHoldId(), 0);
            assertEquals(temp.getSeatNumber(), fetchedSeatHold.getSeatNumber(), 0);
            assertEquals(temp.getMinLevelId(), fetchedSeatHold.getMinLevelId(), 0);
            assertEquals(temp.getMaxLevelId(), fetchedSeatHold.getMaxLevelId(), 0);
            assertEquals(temp.getCustomerEmail(), fetchedSeatHold.getCustomerEmail());
            assertEquals(temp.getConfirmationCode(), fetchedSeatHold.getConfirmationCode());
            assertEquals(temp.getHoldTime(), fetchedSeatHold.getHoldTime());
            assertEquals(temp.getReservationTime(), fetchedSeatHold.getReservationTime());
        }
        assertEquals(count, 1);

        //findByReservationTimeIsNullAndHoldTimeBefore
        seatHolds = seatHoldRepository.findByReservationTimeIsNullAndHoldTimeBefore(Timestamp.from(Instant.now()));
        count = 0;
        for (SeatHold temp : seatHolds) {
        	assertNotNull(temp);
            count++;
        }
        assertEquals(count, 1);

        //Remove
        seatHoldRepository.delete(fetchedSeatHold);
        long levelCount = seatHoldRepository.count();
        assertEquals(levelCount, 0);
    }
}
