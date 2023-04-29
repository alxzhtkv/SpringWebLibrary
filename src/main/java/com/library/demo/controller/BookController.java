package com.library.demo.controller;

import com.library.demo.model.Role;
import com.library.demo.model.Book;
import com.library.demo.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@AllArgsConstructor
public class BookController {
    private final BookRepository bookRepository;


    @PostMapping("/addBook")
    public String addBook(@ModelAttribute Book book,
                          @RequestParam("content1") MultipartFile contentFile,
                          @RequestParam("image1") MultipartFile imageFile) throws IOException {
        System.out.println(book.getAuthor());
        byte[] content = contentFile.getBytes();
        byte[] image = imageFile.getBytes();
        book.setContent(content);
        book.setImage(image);

        bookRepository.save(book);
        return "redirect:/addBook";
    }

}

