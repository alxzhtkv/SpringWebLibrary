package com.library.demo.controller;

import com.library.demo.model.*;
import com.library.demo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@AllArgsConstructor
public class BookController {
    private final BookRepository bookRepository;


    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book,
                          @RequestParam("content1") MultipartFile contentFile,
                          @RequestParam("image1") MultipartFile imageFile) throws IOException {

        byte[] content = contentFile.getBytes();
        byte[] image = imageFile.getBytes();
        book.setContent(content);
        book.setImage(image);

        bookRepository.save(book);
        return "redirect:/addBook";
    }

    @GetMapping("/booksCatalog")
    public String getBooksCatalogPage(Model model){
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);


        return "booksCatalogPage";
    }

    @GetMapping("/booksCatalogUser")
    public String getBooksCatalogUserPage(Model model){
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);


        return "booksCatalogUserPage";
    }




}

