package com.library.demo.controller;

import com.library.demo.model.*;
import com.library.demo.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import untils.FileUploadHelper;
import untils.ImageUploadHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class addRecordsController {
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    private  final PublisherRepository publisherRepository;

    private final ReviewRepository reviewRepository;

    private final RequestRepository requestRepository;
    private final UserBookRepository userBookRepository;


    @PostMapping("/addGenre")
    public String addGenre(Genre genre){
        genreRepository.save(genre);

        return "redirect:/addAuthorAndGenre";
    }

    @PostMapping("/addAuthor")
    public String addAuthor(Author author){
        authorRepository.save(author);

        return "redirect:/addAuthorAndGenre";
    }


    @PostMapping("/addPublisher")
    public String addPublisher(Publisher publisher){
        publisherRepository.save(publisher);

        return "redirect:/addAuthorAndGenre";
    }

    @PostMapping("/addReview")
    public String addReview(@AuthenticationPrincipal(expression = "username") String username, Review review){

        review.setUsername(username);
        reviewRepository.save(review);

        return "redirect:/addReview";
    }


    @PostMapping("/addRequest")
    public String addRequest(@AuthenticationPrincipal(expression = "username") String username, Request request){

        request.setUsername(username);
        requestRepository.save(request);

        return "redirect:/addRequest";
    }
    @PostMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable(value = "id") Long id){
        try {
            authorRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            System.out.println("низя удалять");
            // например, вывод сообщения об ошибке на странице
        }


        return "redirect:/addAuthorAndGenre";
    }

    @PostMapping("/deleteGenre/{id}")
    public String deleteGenre(@PathVariable(value = "id") Long id){
        try {
            genreRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            System.out.println("низя удалять");
            // например, вывод сообщения об ошибке на странице
        }


        return "redirect:/addAuthorAndGenre";
    }

    @PostMapping("/deletePublisher/{id}")
    public String deletePublisher(@PathVariable(value = "id") Long id){
        try {
            publisherRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            System.out.println("низя удалять");
            // например, вывод сообщения об ошибке на странице
        }


        return "redirect:/addAuthorAndGenre";
    }


    @PostMapping("/addUserBook")
    public String getUserBookPage (@AuthenticationPrincipal(expression = "username") String username,
                                   @ModelAttribute UserBook userBook,
                                   HttpServletRequest request,
                                   @RequestParam("content1") MultipartFile contentFile,
                                   @RequestParam("image1") MultipartFile imageFile) throws IOException {

        String imagePath = ImageUploadHelper.uploadImage(imageFile);
        String contentPath = FileUploadHelper.uploadImage(contentFile);

        userBook.setContent(contentPath);
        userBook.setImage(imagePath);
        userBook.setUsername(username);

        userBookRepository.save(userBook);
        return "redirect:/addUserBook";
    }




}
