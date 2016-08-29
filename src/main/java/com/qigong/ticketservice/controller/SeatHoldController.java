package com.qigong.ticketservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qigong.ticketservice.domain.SeatHold;
import com.qigong.ticketservice.service.SeatHoldService;

@Controller
public class SeatHoldController {

    private SeatHoldService seatHoldService;

    @Autowired
    public void setSeatHoldService(SeatHoldService seatHoldService) {
        this.seatHoldService = seatHoldService;
    }

    @RequestMapping(value = "/seatholds", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("seatHolds", seatHoldService.listAllSeatHolds());
        return "seatholds";
    }

    @RequestMapping("seathold/{id}")
    public String showSeatHold(@PathVariable Integer seatHoldId, Model model) {
        model.addAttribute("seatHold", seatHoldService.getSeatHoldBySeatHoldId(seatHoldId));
        return "seatholdshow";
    }

    @RequestMapping("seathold/edit/{id}")
    public String edit(@PathVariable Integer seatHoldId, Model model) {
        model.addAttribute("seatHold", seatHoldService.getSeatHoldBySeatHoldId(seatHoldId));
        return "seatholdform";
    }

    @RequestMapping("seathold/new")
    public String newSeatHold(Model model) {
        model.addAttribute("seatHold", new SeatHold());
        return "seatholdform";
    }

    @RequestMapping(value = "seatHold", method = RequestMethod.POST)
    public String saveSeatHold(SeatHold seatHold) {
        seatHoldService.saveSeatHold(seatHold);
        return "redirect:/seathold/" + seatHold.getSeatHoldId();
    }

    @RequestMapping("seathold/delete/{id}")
    public String delete(@PathVariable Integer id) {
        seatHoldService.deleteSeatHold(id);
        return "redirect:/seatholds";
    }
}
