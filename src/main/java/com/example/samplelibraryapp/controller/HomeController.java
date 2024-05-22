package com.example.samplelibraryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.samplelibraryapp.service.ReservationService;

@Controller
public class HomeController {
    
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/home")
    public String index() {
        return "home";
    }

    @GetMapping("/reservations")
    public String reservations(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        return "reservations";
    }
}
