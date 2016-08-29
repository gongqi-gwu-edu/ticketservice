package com.qigong.ticketservice.service;

import com.qigong.ticketservice.domain.Level;

public interface LevelService {
    Iterable<Level> listAllLevels();

    Level getLevelByLevelId(Integer levelId);

    Level saveLevel(Level level);

    void deleteLevel(Integer levelId);
}
