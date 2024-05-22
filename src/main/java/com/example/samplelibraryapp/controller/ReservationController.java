package com.example.samplelibraryapp.controller;

import java.time.LocalDate;
import java.util.List;

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

import jakarta.servlet.http.HttpSession;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BookService bookService;

    @GetMapping("/user-reservations")
    public String getReservations(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        List<Reservation> reservations = reservationService.findByUsername(username);
        model.addAttribute("username", username);
        model.addAttribute("reservations", reservations);
        return "reservations";
    }

    @PostMapping("/reservations")
    public String addReservation(@RequestParam Long bookId, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        Book book = bookService.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + bookId));
        Reservation reservation = new Reservation(username, LocalDate.now(), book);
        reservationService.save(reservation);
        return "redirect:/user-reservations";
    }

    @PostMapping("/cancel-reservation")
    public String cancelReservation(@RequestParam Long reservationId, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        reservationService.deleteById(reservationId);
        return "redirect:/user-reservations";
    }
}


