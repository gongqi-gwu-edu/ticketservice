package com.qigong.ticketservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.qigong.ticketservice.domain.Level;

public interface LevelRepository extends CrudRepository<Level, Integer> {
    Iterable<Level> findByLevelIdBetween(int maxLevel, int minLevel);
    Level findMaxLevelId();
    Level findMinLevelId();
}
