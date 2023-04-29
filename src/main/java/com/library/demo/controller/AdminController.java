package com.library.demo.controller;


import com.library.demo.model.Genre;
import com.library.demo.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminController {

    private final GenreRepository genreRepository;
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

        return "addBooksPage";
    }


    @GetMapping("/addAuthorAndGenre")
    public String getAuthorAndGenrePage(){
        return "addAuthorAndGenre";
    }

//    @GetMapping("/userManagement")
//    public String getUserManagementPage(){
//        return "userManagementPage";
//    }


}
