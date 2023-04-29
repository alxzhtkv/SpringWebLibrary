package com.library.demo.controller;

import com.library.demo.model.User;
import com.library.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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


    @PostMapping("/userManagement/{username}")
    public String deleteUser(@PathVariable(value = "username") String username) {
//        User user = userRepository.findByUsername(username);
        userRepository.delete(userRepository.findByUsername(username));
        return "redirect:/userManagement";
    }
}
