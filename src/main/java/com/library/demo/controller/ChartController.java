package com.library.demo.controller;


import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;


@Controller
public class ChartController {

    @GetMapping("/chart")
    public String showChart(Model model) {
        List<ChartDataItem> chartData = // Retrieve or generate your data as a list of objects
                Arrays.asList(
                        new ChartDataItem("Label 1", 25),
                        new ChartDataItem("Label 2", 50),
                        new ChartDataItem("Label 3", 75)
                );

        model.addAttribute("chartData", chartData);
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