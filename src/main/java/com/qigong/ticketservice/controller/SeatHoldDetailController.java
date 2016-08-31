package com.qigong.ticketservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qigong.ticketservice.service.SeatHoldDetailService;

@Controller
public class SeatHoldDetailController {

    private SeatHoldDetailService seatHoldDetailService;

    @Autowired
    public void setSeatHoldDetailService(SeatHoldDetailService seatHoldDetailService) {
        this.seatHoldDetailService = seatHoldDetailService;
    }

    @RequestMapping("seatholddetail/{id}")
    public String showSeatHoldDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("seatHoldDetail", seatHoldDetailService.getSeatHoldDetailById(id));
        return "seatholddetail";
    }
}
