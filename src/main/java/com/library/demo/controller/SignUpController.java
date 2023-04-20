package com.library.demo.controller;

import com.library.demo.model.User;
import com.library.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String getSignUp(){
        return "signup";
    }
    @PostMapping("/signup")
    public String signUpUser(User user){

        userRepository.save(user);
        return "redirect:/signup";

    }


}
