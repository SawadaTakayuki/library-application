package com.example.samplelibraryapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private int publicYear;
    private String category;

    public Book(String title, String author, int publicYear, String category) {
        this.title = title;
        this.author = author;
        this.publicYear = publicYear;
        this.category = category;
    }
}