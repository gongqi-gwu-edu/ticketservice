package com.qigong.ticketservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qigong.ticketservice.domain.SeatHoldDetail;
import com.qigong.ticketservice.service.SeatHoldDetailService;

@Controller
public class SeatHoldDetailController {

    private SeatHoldDetailService seatHoldDetailService;

    @Autowired
    public void setSeatHoldDetailService(SeatHoldDetailService seatHoldDetailService) {
        this.seatHoldDetailService = seatHoldDetailService;
    }

    @RequestMapping(value = "/seatholddetails", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("seatHoldDetails", seatHoldDetailService.listAllSeatHoldDetails());
        return "seatholddetails";
    }

    @RequestMapping("seatholddetail/{id}")
    public String showSeatHoldDetail(@PathVariable Integer id, Model model) {
        model.addAttribute("seatHoldDetail", seatHoldDetailService.getSeatHoldDetailById(id));
        return "seatholddetailshow";
    }

    @RequestMapping("seatholddetail/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("seatHoldDetail", seatHoldDetailService.getSeatHoldDetailById(id));
        return "seatholddetailform";
    }

    @RequestMapping("seatholddetail/new")
    public String newSeatHoldDetail(Model model) {
        model.addAttribute("seatHoldDetail", new SeatHoldDetail());
        return "seatholddetaildform";
    }

    @RequestMapping(value = "seatHoldDetail", method = RequestMethod.POST)
    public String saveSeatHoldDetail(SeatHoldDetail seatHoldDetail) {
    	seatHoldDetailService.saveSeatHoldDetail(seatHoldDetail);
        return "redirect:/seatholddetail/" + seatHoldDetail.getId();
    }

    @RequestMapping("seatholddetail/delete/{id}")
    public String delete(@PathVariable Integer id) {
    	seatHoldDetailService.deleteSeatHoldDetail(id);
        return "redirect:/seatholddetails";
    }
}
