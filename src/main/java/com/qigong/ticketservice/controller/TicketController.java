package com.qigong.ticketservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qigong.ticketservice.domain.SeatHold;
import com.qigong.ticketservice.service.TicketService;

@Controller
public class TicketController {

    private TicketService ticketService;

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping(value = "/availableseatsnumber", method = RequestMethod.GET)
    public String getAvailableSeatsNumber(Model model) {
        model.addAttribute("availableSeatsNumber", ticketService.numSeatsAvailable(Optional.empty()));
        return "ticket";
    }

    @RequestMapping(value = "/availableseatsnumber/{levelId}", method = RequestMethod.GET)
    public String getAvailableSeatsNumber(@PathVariable Integer levelId, Model model) {
        model.addAttribute("availableSeatsNumber", ticketService.numSeatsAvailable(Optional.of(levelId)));
        return "ticket";
    }

    @RequestMapping("seathold/new")
    public String newSeatHold(Model model) {
        model.addAttribute("seatHold", new SeatHold());
        return "seatholdform";
    }

    @RequestMapping(value = "seatHold", method = RequestMethod.POST)
    public String saveSeatHold(SeatHold seatHold) {
        seatHold = ticketService.findAndHoldSeats(seatHold.getSeatNumber(), 
                                                  seatHold.getMinLevelId() == null ? Optional.empty() : Optional.of(seatHold.getMinLevelId()), 
                                                  seatHold.getMaxLevelId() == null ? Optional.empty() : Optional.of(seatHold.getMaxLevelId()), 
                                                  seatHold.getCustomerEmail());
        return "redirect:/seathold/" + seatHold.getSeatHoldId();
    }

    @RequestMapping("seathold/commit/{seatHoldId}")
    public String commitSeatHold(@PathVariable Integer seatHoldId, @PathVariable String customerEmail, Model model) {
        ticketService.reserveSeats(seatHoldId, customerEmail);
        return "redirect:/seatholds";
    }
}
