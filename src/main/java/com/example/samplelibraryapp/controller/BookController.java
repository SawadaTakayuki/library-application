package com.example.samplelibraryapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samplelibraryapp.model.Book;
import com.example.samplelibraryapp.service.BookService;

import jakarta.servlet.http.HttpSession;

@Controller
public class BookController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping("/books")
    public String getAllBooks(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        model.addAttribute("books", bookService.findAll());
        return "books";
    }
    
    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Optional<Book> book = bookService.findById(id);
        if (book.isPresent()) {
            model.addAttribute("book", book.get());
            return "book-details";
        } else {
            return "redirect:/books";
        }
    }
}


