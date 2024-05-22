package com.example.samplelibraryapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.samplelibraryapp.model.Book;
import com.example.samplelibraryapp.service.BookService;
import com.example.samplelibraryapp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminCnotroller {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/admin")
		public String adimnHome (HttpSession session) {
			String username = (String) session.getAttribute("username");
			if (username == null || !isAdmin(username)) {
				return "redirect:/login";
			}
			return "admin";	
	}
	
	@GetMapping("/admin/add-books")
	public String AddBooks (HttpSession session) {
		String username = (String) session.getAttribute("username");
		if(username == null || !isAdmin(username)) {
			return "redirect:/login";
		}
		return  "add-books";
	}
	
	@PostMapping("/admin/add-books")
	public String addBooks(@RequestParam String title, @RequestParam String author, 
            							@RequestParam int publicYear, @RequestParam String category, 
            							HttpSession session) {
		String username = (String) session.getAttribute("username");
		if(username == null || !isAdmin(username)) {
			return "redirect:/login";
		}
		Book book = new Book(title, author, publicYear, category);
		bookService.save(book);
		return "redirect:/admin";
	}
	
	@GetMapping("/admin/delete-user")
    public String deleteUserForm(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null || !isAdmin(username)) {
            return "redirect:/login";
        }
        model.addAttribute("users", userService.findAll());
        return "delete-user";
    }

    @PostMapping("/admin/delete-user")
    public String deleteUser(@RequestParam Long userId, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null || !isAdmin(username)) {
            return "redirect:/login";
        } 
        userService.deleteById(userId);
        return "redirect:/admin";
    }
    
    @GetMapping("/admin/delete-books")
    public String deleteBooForm(HttpSession session, Model model) {
    	String username = (String) session.getAttribute("username");
    	if (username == null || !isAdmin(username)) {
    		return "redirect:/login";
    	}
    	model.addAttribute("books", bookService.findAll());
    	return "delete-books";
    } 
    
    @PostMapping("/admin/delete-books")
    public String deleteBook(@RequestParam Long bookId, HttpSession session, Model model) {
    	 String username = (String) session.getAttribute("username");
         if (username == null || !isAdmin(username)) {
             return "redirect:/login";
         } 
         bookService.deleteById(bookId);
         model.addAttribute("books", bookService.findAll());
         return "delete-books";
    }
    
    private boolean isAdmin(String username) {
        // 管理者ユーザーをチェックするためのロジックをここに追加
        // 例えば、特定のユーザー名を管理者とする場合:
        return "admin".equals(username);
    }


}
