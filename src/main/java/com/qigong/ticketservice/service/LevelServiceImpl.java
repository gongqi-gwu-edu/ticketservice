package com.qigong.ticketservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qigong.ticketservice.domain.Level;
import com.qigong.ticketservice.repository.LevelRepository;

@Service
public class LevelServiceImpl implements LevelService {
    private LevelRepository levelRepository;

    @Autowired
    public void setLevelRepository(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Iterable<Level> listAllLevels() {
        return levelRepository.findAll();
    }

    @Override
    public Level getLevelByLevelId(Integer levelId) {
        return levelRepository.findOne(levelId);
    }

    @Override
    public Level saveLevel(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public void deleteLevel(Integer levelId) {
        levelRepository.delete(levelId);
        
    }
}
