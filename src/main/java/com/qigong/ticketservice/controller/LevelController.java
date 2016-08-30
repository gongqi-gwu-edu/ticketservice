package com.qigong.ticketservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qigong.ticketservice.service.LevelService;
import com.qigong.ticketservice.service.TicketService;

@Controller
public class LevelController {

    private LevelService levelService;
    private TicketService ticketService;

    @Autowired
    public void setLevelService(LevelService levelService) {
        this.levelService = levelService;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "/levels", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("levels", levelService.listAllLevels());
        model.addAttribute("availableSeatsNumber", ticketService.numSeatsAvailable(Optional.empty()));
        return "levels";
    }

    @RequestMapping("level/{levelId}")
    public String showLevel(@PathVariable Integer levelId, Model model) {
        model.addAttribute("level", levelService.getLevelByLevelId(levelId));
        model.addAttribute("availableSeatsNumber", ticketService.numSeatsAvailable(Optional.of(levelId)));
        return "level";
    }

    /*
    @RequestMapping("level/edit/{levelId}")
    public String edit(@PathVariable Integer levelId, Model model) {
        model.addAttribute("level", levelService.getLevelByLevelId(levelId));
        return "levelform";
    }

    @RequestMapping("level/new")
    public String newLevel(Model model) {
        model.addAttribute("level", new Level());
        return "levelform";
    }

    @RequestMapping(value = "level", method = RequestMethod.POST)
    public String saveLevel(Level level) {
        levelService.saveLevel(level);
        return "redirect:/level/" + level.getLevelId();
    }

    @RequestMapping("level/delete/{levelId}")
    public String delete(@PathVariable Integer levelId) {
        levelService.deleteLevel(levelId);
        return "redirect:/levels";
    }*/
}
