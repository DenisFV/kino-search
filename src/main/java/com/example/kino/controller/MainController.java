package com.example.kino.controller;

import com.example.kino.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    private MovieService movieService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/main")
    public String main(@RequestParam(name = "filterDate", required = false) String date, Map<String, Object> model) {
        model.put("movies", movieService.findAllByFilterDate(date));
        return "main";
    }

    @PostMapping("/main")
    public String update(@RequestParam(name = "filterDate") String date, Map<String, Object> model) {
        model.put("movies", movieService.findAllByFilterDate(date));
        return "redirect:/main?filterDate=" + date;
    }
}
