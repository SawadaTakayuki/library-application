package com.example.samplelibraryapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private LocalDate reservationDate;

    @ManyToOne
    private Book book;

    public Reservation(String username, LocalDate reservationDate, Book book) {
        this.username = username;
        this.reservationDate = reservationDate;
        this.book = book;
    }
}

