package com.library.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReaderController {

    @GetMapping("/users")
    public String getUserPage(Model model){
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
        return "userPage";
    }
}