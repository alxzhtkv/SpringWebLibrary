package com.library.demo.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class AdminController {
    @GetMapping("/admins")
    public String getAdminPage(Model model){
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
        return "adminPage";
    }

    @GetMapping("/addBook")
    public String getAddBooksPage(){
        return "addBooksPage";
    }
}
