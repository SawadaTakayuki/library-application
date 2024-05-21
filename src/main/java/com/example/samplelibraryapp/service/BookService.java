package com.example.samplelibraryapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.samplelibraryapp.model.Book;
import com.example.samplelibraryapp.model.Reservation;
import com.example.samplelibraryapp.repository.BookRepository;
import com.example.samplelibraryapp.repository.ReservationRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Book> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<Long> reservedBookIds = reservations.stream()
                                                 .map(reservation -> reservation.getBook().getId())
                                                 .collect(Collectors.toList());
        return bookRepository.findAll().stream()
                             .filter(book -> !reservedBookIds.contains(book.getId()))
                             .collect(Collectors.toList());
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> allBooks = bookRepository.findAll();
        List<Book> filteredBooks = allBooks.stream()
            .filter(book -> (book.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(keyword.toLowerCase())))
            .collect(Collectors.toList());

        List<Reservation> reservations = reservationRepository.findAll();
        List<Long> reservedBookIds = reservations.stream()
                                                 .map(reservation -> reservation.getBook().getId())
                                                 .collect(Collectors.toList());

        return filteredBooks.stream()
                            .filter(book -> !reservedBookIds.contains(book.getId()))
                            .collect(Collectors.toList());
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public boolean existsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
}


