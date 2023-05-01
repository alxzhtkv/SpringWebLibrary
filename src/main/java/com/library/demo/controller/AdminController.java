package com.library.demo.controller;


import com.library.demo.model.Author;
import com.library.demo.model.Book;
import com.library.demo.model.Genre;
import com.library.demo.model.Publisher;
import com.library.demo.repository.AuthorRepository;
import com.library.demo.repository.BookRepository;
import com.library.demo.repository.GenreRepository;
import com.library.demo.repository.PublisherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final BookRepository bookRepository;
    @GetMapping("/admins")
    public String getAdminPage(Model model){
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
        return "adminPage";
    }

    @GetMapping("/addBook")
    public String getAddBooksPage(Model model){
        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);

        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);

        Iterable<Publisher> publishers = publisherRepository.findAll();
        model.addAttribute("publishers", publishers);

        return "addBooksPage";
    }


    @GetMapping("/addAuthorAndGenre")
    public String getAuthorAndGenrePage(Model model){

        Iterable<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);

        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);

        Iterable<Publisher> publishers = publisherRepository.findAll();
        model.addAttribute("publishers", publishers);

        return "addAuthorAndGenre";
    }



    @GetMapping("/firstArticle")
    public String getFirstArticlePage(){
        return "firstArticle";
    }

    @GetMapping("/secondArticle")
    public String getSecondArticlePage(){
        return "secondArticle";
    }

    @GetMapping("/thirdArticle")
    public String getThirdArticlePage(){
        return "thirdArticle";
    }



    @GetMapping("/search")
    public String search(@RequestParam("search") String search, Model model) {
        List<Book> books = bookRepository.searchBooks(search);
        model.addAttribute("books", books);
        return "booksCatalogPage";
    }


//    @GetMapping("/requestManagement")
//    public String getRequestManagementPage(){
//        return "requestManagement";
//    }


}
