package com.library.demo.controller;


import com.library.demo.model.Book;
import com.library.demo.repository.BookRepository;
import com.library.demo.service.BookService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
@Controller
public class ChartController {


    private final BookService bookService;
    private final BookRepository bookRepository;

    @GetMapping("/chart")
    public String showChart(Model model) {


        List<Book> books = bookService.findThreeFavouriteBooks();
        List<ChartDataItem> chartData = // Retrieve or generate your data as a list of objects
                Arrays.asList(
                        new ChartDataItem(books.get(0).getName(), books.get(0).getAvgRating()),
                        new ChartDataItem(books.get(1).getName(), books.get(1).getAvgRating()),
                        new ChartDataItem(books.get(2).getName(), books.get(2).getAvgRating())
                );

        Iterable<Book> bookList= bookRepository.findAllByViewCountDescr();
        model.addAttribute("chartData", chartData);
        model.addAttribute("bookList", bookList);



        return "pieChartPage";
    }

    // Define your data object as needed
    @Data
    public static class ChartDataItem {
        private String label;
        private int value;



        // Constructor, getters, and setters

        public ChartDataItem(String label, int value) {
            this.label = label;
            this.value = value;
        }

        // Getters and setters
    }
}