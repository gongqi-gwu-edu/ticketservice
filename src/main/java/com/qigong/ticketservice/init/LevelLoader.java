package com.qigong.ticketservice.init;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.qigong.ticketservice.domain.Level;
import com.qigong.ticketservice.repository.LevelRepository;

@Component
public class LevelLoader implements ApplicationListener<ContextRefreshedEvent> {

    private LevelRepository levelRepository;

    private Logger log = Logger.getLogger(LevelLoader.class);

    @Autowired
    public void setLevelRepository(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Level level;

        level = new Level();
        level.setLevelId(1);
        level.setLevelName("Orchestra");
        level.setPrice(new BigDecimal("100.00"));
        level.setRowNumber(25);
        level.setSeatNumberInRow(50);
        levelRepository.save(level);

        log.info("Saved Level: " + level);

        level = new Level();
        level.setLevelId(2);
        level.setLevelName("Main");
        level.setPrice(new BigDecimal("75.00"));
        level.setRowNumber(20);
        level.setSeatNumberInRow(100);
        levelRepository.save(level);

        log.info("Saved Level: " + level);

        level = new Level();
        level.setLevelId(3);
        level.setLevelName("Balcony 1");
        level.setPrice(new BigDecimal("50.00"));
        level.setRowNumber(15);
        level.setSeatNumberInRow(100);
        levelRepository.save(level);

        log.info("Saved Level: " + level);

        level = new Level();
        level.setLevelId(4);
        level.setLevelName("Balcony 2");
        level.setPrice(new BigDecimal("40.00"));
        level.setRowNumber(15);
        level.setSeatNumberInRow(100);
        levelRepository.save(level);

        log.info("Saved Level: " + level);
    }
}
