package com.library.demo.controller;

import com.library.demo.model.Author;
import com.library.demo.model.Genre;
import com.library.demo.model.Publisher;
import com.library.demo.repository.AuthorRepository;
import com.library.demo.repository.GenreRepository;
import com.library.demo.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class addRecordsController {
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private  final PublisherRepository publisherRepository;


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


}
