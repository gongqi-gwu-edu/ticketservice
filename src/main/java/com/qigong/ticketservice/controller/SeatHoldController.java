package com.qigong.ticketservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping("seathold/{seatHoldId}")
    public String showSeatHold(@PathVariable Integer seatHoldId, Model model) {
        model.addAttribute("seatHold", seatHoldService.getSeatHoldBySeatHoldId(seatHoldId));
        return "seathold";
    }

    @RequestMapping("seathold/delete/{seatHoldId}")
    public String delete(@PathVariable Integer seatHoldId) {
        seatHoldService.deleteSeatHold(seatHoldId);
        return "redirect:/seatholds";
    }
}
