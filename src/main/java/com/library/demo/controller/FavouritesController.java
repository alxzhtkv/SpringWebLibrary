package com.library.demo.controller;

import com.library.demo.model.Book;
import com.library.demo.model.Favourites;
import com.library.demo.model.Genre;
import com.library.demo.model.UserBook;
import com.library.demo.repository.BookRepository;
import com.library.demo.repository.FavouritesRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Controller
public class FavouritesController {
    private final FavouritesRepository favouritesRepository;
    private final BookRepository bookRepository;

    @GetMapping("/addFavourite/{id}")
    public String addFavourite(HttpServletRequest request, @PathVariable(value = "id") Long id, @AuthenticationPrincipal(expression = "username") String username){
        Favourites favourites = new Favourites(username,id);
        if(!favouritesRepository.existsByUsernameAndBookId(username,id)){
            favouritesRepository.save(favourites);
            Book book = bookRepository.getBookById(id);
            int favRating = book.getAvgRating() + 1;
            book.setAvgRating(favRating);
            bookRepository.save(book);

        }

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }


    @GetMapping("/favouriteUserBooks")
    public String getFavouriteUserBooksPage(@AuthenticationPrincipal(expression = "username") String username, Model model){


        ArrayList<Long> booksIds = favouritesRepository.findBookIdsByUsername(username);
        Iterable<Book> books = bookRepository.getBooksByIds(booksIds);

        model.addAttribute("books",books);


        return "favouritesPage";
    }


    @Transactional
    @GetMapping("/deleteFavouriteBook/{id}")
    public String deleteRequestUser(HttpServletRequest request, @AuthenticationPrincipal(expression = "username") String username, @PathVariable(value = "id") Long id){
        favouritesRepository.deleteByUsernameAndBookId(username,id);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
