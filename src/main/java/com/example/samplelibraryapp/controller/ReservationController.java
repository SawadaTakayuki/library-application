package com.example.samplelibraryapp.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samplelibraryapp.model.Book;
import com.example.samplelibraryapp.model.Reservation;
import com.example.samplelibraryapp.service.BookService;
import com.example.samplelibraryapp.service.ReservationService;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BookService bookService;

    @GetMapping("/user-reservations")
    public String getReservations(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("reservations", reservationService.findByUsername(username));
        return "reservations";
    }

    @PostMapping("/reservations")
    public String addReservation(@RequestParam Long bookId, @RequestParam String username) {
        Book book = bookService.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookId));
        Reservation reservation = new Reservation(username, LocalDate.now(), book);
        reservationService.save(reservation);
        return "redirect:/user-reservations?username=" + username;
    }

    @PostMapping("/cancel-reservation")
    public String cancelReservation(@RequestParam Long reservationId, @RequestParam String username) {
        reservationService.deleteById(reservationId);
        return "redirect:/user-reservations?username=" + username;
    }
}

