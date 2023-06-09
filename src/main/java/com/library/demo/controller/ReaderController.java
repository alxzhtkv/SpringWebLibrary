package com.library.demo.controller;

import com.library.demo.model.*;
import com.library.demo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class ReaderController {
    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    private final GenreRepository genreRepository;
    private final UserBookRepository userBookRepository;
    private final RequestRepository requestRepository;

    private final FavouritesRepository favouritesRepository;

    @GetMapping("/users")
    public String getUserPage(@AuthenticationPrincipal(expression = "username") String username) {
        return "userPage";
    }

    @GetMapping("/addReview")
    public String getAddReviewPage(Model model) {

        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);

        Iterable<Review> reviews = reviewRepository.findAll();
        model.addAttribute("reviews", reviews);
        return "addReview";
    }

    @GetMapping("/addRequest")
    public String getRequestPage(@AuthenticationPrincipal(expression = "username") String username, Model model) {

        Iterable<Request> userRequests = requestRepository.getListOfUserRequestsByUsername(username);
        model.addAttribute("userRequests", userRequests);
        return "addRequest";
    }

    @GetMapping("/addUserBook")
    public String getUserBookPage(@AuthenticationPrincipal(expression = "username") String username, Model model) {
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);

        Iterable<UserBook> userBooks = userBookRepository.getListOfUserBooksByUsername(username);
        model.addAttribute("userBooks", userBooks);
        return "userBooksPage";
    }
}
