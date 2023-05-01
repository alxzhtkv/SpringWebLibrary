package com.library.demo.controller;

import com.library.demo.model.Book;
import com.library.demo.model.Genre;
import com.library.demo.model.Review;
import com.library.demo.model.UserBook;
import com.library.demo.repository.BookRepository;
import com.library.demo.repository.GenreRepository;
import com.library.demo.repository.ReviewRepository;
import com.library.demo.repository.UserBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class ReaderController {
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    private final GenreRepository genreRepository;
    private final UserBookRepository userBookRepository;

    @GetMapping("/users")
    public String getUserPage(@AuthenticationPrincipal(expression = "username") String username){
        return "userPage";
    }

    @GetMapping("/addReview")
    public String getAddReviewPage(Model model){

        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);

        Iterable<Review> reviews = reviewRepository.findAll();

//        ArrayList<Long> bookIds = reviewRepository.getAllBookIds();
//        ArrayList<String> booksNames = bookRepository.getListOfBookNames(bookIds);
//
//        model.addAttribute("booksNames", booksNames);
        model.addAttribute("reviews",reviews);
//        if(bookRepository.findById())

        return "addReview";
    }
    @GetMapping("/addRequest")
    public String getRequestPage(@AuthenticationPrincipal(expression = "username") String username){
        return "addRequest";
    }

    @GetMapping("/addUserBook")
    public String getUserBookPage(@AuthenticationPrincipal(expression = "username") String username,Model model){
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres",genres);

        Iterable<UserBook> userBooks = userBookRepository.getListOfUserBooksByUsername(username);
        model.addAttribute("userBooks",userBooks);



        return "userBooksPage";
    }






}
