package com.library.demo.controller;


import com.library.demo.model.Request;
import com.library.demo.repository.RequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class RequestController {
    private final RequestRepository requestRepository;


    @GetMapping("/requestManagement")
    public String getRequestManagementPage(Model model){
        Iterable<Request> requests = requestRepository.findAll();
        model.addAttribute("requests", requests);
        return "requestManagement";
    }

    @PostMapping("/requestManagement/{id}")
    public String deleteRequest(@PathVariable(value = "id") Long id){
       requestRepository.deleteById(id);

        return "redirect:/requestManagement";
    }

}
