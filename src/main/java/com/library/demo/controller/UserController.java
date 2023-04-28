package com.library.demo.controller;

import com.library.demo.model.User;
import com.library.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserController {


    private final UserRepository userRepository;
    @GetMapping("/userManagement")
    public String getUserPage(Model model){
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "userManagementPage";
    }


//    @GetMapping("/see")
//    public String show(Model model) {
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
//        return "userPage";
//    }
}
