package com.library.demo.controller;

import com.library.demo.model.User;
import com.library.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/users")
    public String getUserPage(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userPage";
    }


//    @GetMapping("/see")
//    public String show(Model model) {
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
//        return "userPage";
//    }
}
