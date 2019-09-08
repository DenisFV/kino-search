package com.example.kino.config;

import com.example.kino.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScheduledTasks {

    private MovieService movieService;

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void getNewMoviesTop() {
        movieService.findAllByFilterDate(LocalDate.now().toString());
    }
}
