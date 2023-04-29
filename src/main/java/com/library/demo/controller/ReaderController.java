package com.library.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReaderController {

    @GetMapping("/users")
    public String getUserPage(@AuthenticationPrincipal(expression = "username") String username){
//        System.out.println(username);
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
        return "userPage";
    }

    @GetMapping("/addReview")
    public String getAddReviewPage(@AuthenticationPrincipal(expression = "username") String username){
        System.out.println(username);
//        Iterable<User> users = userRepository.findAll();
//        model.addAttribute("users", users);
        return "addReview";
    }


}
