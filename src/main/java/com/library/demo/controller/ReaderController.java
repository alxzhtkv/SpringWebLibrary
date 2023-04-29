package com.library.demo.controller;

import com.library.demo.model.Book;
import com.library.demo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class ReaderController {
    private final BookRepository bookRepository;

    @GetMapping("/users")
    public String getUserPage(@AuthenticationPrincipal(expression = "username") String username){
        return "userPage";
    }

    @GetMapping("/addReview")
    public String getAddReviewPage(Model model){

        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);

        return "addReview";
    }


}
