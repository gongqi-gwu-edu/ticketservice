package com.qigong.ticketservice.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qigong.ticketservice.configuration.RepositoryConfiguration;
import com.qigong.ticketservice.domain.Level;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class LevelRepositoryTest {

    private LevelRepository levelRepository;

    @Autowired
    public void setLevelRepository(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Test
    public void test() {
    	//New
        Level level = new Level();
        level.setLevelName("Orchestra");
        level.setPrice(new BigDecimal("100.00"));
        level.setRowNumber(25);
        level.setSeatNumberInRow(50);

        assertNull(level.getLevelId());
        levelRepository.save(level);
        assertNotNull(level.getLevelId());

        //Find One
        Level fetchedLevel = levelRepository.findOne(level.getLevelId());

        assertNotNull(fetchedLevel);
        assertEquals(level.getLevelId(), fetchedLevel.getLevelId(), 0);
        assertEquals(level.getLevelName(), fetchedLevel.getLevelName());
        assertEquals(level.getPrice(), fetchedLevel.getPrice());
        assertEquals(level.getRowNumber(), fetchedLevel.getRowNumber());
        assertEquals(level.getSeatNumberInRow(), fetchedLevel.getSeatNumberInRow());

        //Update
        fetchedLevel.setLevelName("New Name");
        levelRepository.save(fetchedLevel);
        Level fetchedUpdatedLevel = levelRepository.findOne(fetchedLevel.getLevelId());
        assertEquals(fetchedLevel.getLevelName(), fetchedUpdatedLevel.getLevelName());

        //Count
        long levelCount = levelRepository.count();
        assertEquals(levelCount, 1);

        //Add one more Level
        Level level2 = new Level();
        level2.setLevelName("Main");
        level2.setPrice(new BigDecimal("75.00"));
        level2.setRowNumber(20);
        level2.setSeatNumberInRow(100);
        levelRepository.save(level2);

        //Find All
        Iterable<Level> levels = levelRepository.findAll();
        int count = 0;
        for (Level temp : levels) {
        	assertNotNull(temp);
            count++;
        }
        assertEquals(count, 2);

        //Find Between Min Level and Max Level
        levels = levelRepository.findByLevelIdBetween(1, 4);
        count = 0;
        for (Level temp : levels) {
        	assertNotNull(temp);
            count++;
        }
        assertEquals(count, 2);

        //Find Max Level
        fetchedLevel = levelRepository.findFirstByOrderByLevelIdDesc();

        assertNotNull(fetchedLevel);
        assertEquals(fetchedLevel.getLevelId(), 2, 0);

        //Remove
        levelRepository.delete(fetchedLevel);
        levelCount = levelRepository.count();
        assertEquals(levelCount, 1);

        //Find Min Level
        fetchedLevel = levelRepository.findFirstByOrderByLevelIdAsc();

        assertNotNull(fetchedLevel);
        assertEquals(fetchedLevel.getLevelId(), 1, 0);

        //Remove
        levelRepository.delete(fetchedLevel);
        levelCount = levelRepository.count();
        assertEquals(levelCount, 0);
    }
}
