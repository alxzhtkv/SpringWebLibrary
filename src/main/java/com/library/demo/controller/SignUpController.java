package com.library.demo.controller;

import com.library.demo.model.Role;
import com.library.demo.model.User;
import com.library.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SignUpController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String getSignUp(){
        return "signup";
    }
    @PostMapping("/signup")
    public String signUpUser(User user, Model model){

        if(!userRepository.existsByUsername(user.getUsername())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(true);
            user.getRoles().add(Role.USER);
            userRepository.save(user);


            String message = "Вы успешно зарегистрировались!";
            model.addAttribute("successMessage", message);
            return "redirect:/signup?success";

        }else {
            String message = "Пользователь с данным usermame уже зарегистирован";
            model.addAttribute("errorMessage", message);
            return "redirect:/signup?error";
//            String message = "Пользователь с данным usermame" + user.getUsername() + " уже зарегистрирован, выберите новый";
//            String script = String.format("$.confirm({title: 'Регистрация', content: '%s', type: 'green'});", message);
//            model.addAttribute("script", script);
        }



//        return "redirect:/signup";
    }


}
