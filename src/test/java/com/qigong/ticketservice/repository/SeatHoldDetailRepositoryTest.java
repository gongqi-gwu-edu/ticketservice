package com.qigong.ticketservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qigong.ticketservice.configuration.RepositoryConfiguration;
import com.qigong.ticketservice.domain.Level;
import com.qigong.ticketservice.domain.SeatHoldDetail;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class SeatHoldDetailRepositoryTest {

	private LevelRepository levelRepository;
    private SeatHoldDetailRepository seatHoldDetailRepository;

    @Autowired
    public void setLevelRepository(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Autowired
    public void setSeatHoldDetailRepository(SeatHoldDetailRepository seatHoldDetailRepository) {
        this.seatHoldDetailRepository = seatHoldDetailRepository;
    }

    @Test
    public void test() {
    	//New
        Level level = new Level();
    	level.setLevelName("Orchestra");
    	level.setPrice(new BigDecimal("100.00"));
        level.setRowNumber(25);
        level.setSeatNumberInRow(50);
        level = levelRepository.save(level);
        SeatHoldDetail seatHoldDetail = new SeatHoldDetail(level, 1);

        assertNull(seatHoldDetail.getId());
        seatHoldDetail = seatHoldDetailRepository.save(seatHoldDetail);
        assertNotNull(seatHoldDetail.getId());

        //Find
        SeatHoldDetail fetchedSeatHoldDetail = seatHoldDetailRepository.findOne(seatHoldDetail.getId());

        assertNotNull(fetchedSeatHoldDetail);
        assertEquals(seatHoldDetail.getId(), fetchedSeatHoldDetail.getId(), 0);
        assertEquals(seatHoldDetail.getSeatNumber(), fetchedSeatHoldDetail.getSeatNumber(), 0);

        //Find All
        Iterable<SeatHoldDetail> seatHoldDetails = seatHoldDetailRepository.findAll();
        int count = 0;
        for (SeatHoldDetail temp : seatHoldDetails) {
            count++;
            assertNotNull(temp);
            assertEquals(temp.getId(), fetchedSeatHoldDetail.getId(), 0);
            assertEquals(temp.getSeatNumber(), fetchedSeatHoldDetail.getSeatNumber(), 0);
        }
        assertEquals(count, 1);

        //findByLevel
        seatHoldDetails = seatHoldDetailRepository.findByLevel(level);
        count = 0;
        for (SeatHoldDetail temp : seatHoldDetails) {
            assertNotNull(temp);
            count += temp.getSeatNumber();
        }
        assertEquals(count, 1);

        //Remove
        seatHoldDetailRepository.delete(fetchedSeatHoldDetail);
        long levelCount = seatHoldDetailRepository.count();
        assertEquals(levelCount, 0);
    }
}
