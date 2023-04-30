package com.library.demo.controller;

import com.library.demo.model.*;
import com.library.demo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class addRecordsController {
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private  final PublisherRepository publisherRepository;

    private final ReviewRepository reviewRepository;

    private final RequestRepository requestRepository;


    @PostMapping("/addGenre")
    public String addGenre(Genre genre){
        genreRepository.save(genre);

        return "redirect:/addAuthorAndGenre";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(Author author){
        authorRepository.save(author);

        return "redirect:/addAuthorAndGenre";
    }


    @PostMapping("/addPublisher")
    public String addPublisher(Publisher publisher){
        publisherRepository.save(publisher);

        return "redirect:/addAuthorAndGenre";
    }

    @PostMapping("/addReview")
    public String addReview(@AuthenticationPrincipal(expression = "username") String username, Review review){

        review.setUsername(username);
        reviewRepository.save(review);

        return "redirect:/addReview";
    }


    @PostMapping("/addRequest")
    public String addRequest(@AuthenticationPrincipal(expression = "username") String username, Request request){

        request.setUsername(username);
        requestRepository.save(request);

        return "redirect:/addRequest";
    }



}
