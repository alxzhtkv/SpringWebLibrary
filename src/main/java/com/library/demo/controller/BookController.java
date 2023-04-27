package com.library.demo.controller;

import com.library.demo.model.Role;
import com.library.demo.model.Book;
import com.library.demo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class BookController {
    private final BookRepository bookRepository;


    @PostMapping("/addBook")
    public String addBook(Book book, Model model) {

        bookRepository.save(book);
        return "redirect:/addBook";
    }

}

