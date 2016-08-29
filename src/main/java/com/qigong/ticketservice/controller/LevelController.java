package com.qigong.ticketservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qigong.ticketservice.domain.Level;
import com.qigong.ticketservice.service.LevelService;

@Controller
public class LevelController {

    private LevelService levelService;

    @Autowired
    public void setLevelService(LevelService levelService) {
        this.levelService = levelService;
    }

    @RequestMapping(value = "/levels", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("levels", levelService.listAllLevels());
        return "levels";
    }

    @RequestMapping("level/{levelId}")
    public String showLevel(@PathVariable Integer levelId, Model model) {
        model.addAttribute("level", levelService.getLevelByLevelId(levelId));
        return "level";
    }

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
    }
}
