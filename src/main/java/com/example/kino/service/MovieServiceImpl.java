package com.example.kino.service;

import com.example.kino.config.util.ParsingMovies;
import com.example.kino.entity.MovieEntity;
import com.example.kino.dao.MovieDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

    private static HashMap<LocalDate, List<MovieEntity>> cacheMovies = new HashMap<>(30);
    private MovieDao movieDao;

    @Autowired
    public void setMovieDao(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public void save(MovieEntity movieEntity) {
        movieDao.save(movieEntity);
    }

    @Override
    public List<MovieEntity> findAllByFilterDate(String date) {
        LocalDate filterDate = date == null ? LocalDate.now() : LocalDate.parse(date);
        filterDate = filterDate.isAfter(LocalDate.now()) ? LocalDate.now() : filterDate;

        List<MovieEntity> movies = cacheMovies.get(filterDate);
        if (movies != null) return movies;

        List<MovieEntity> allByFilterDate = movieDao.findAllByFilterDate(filterDate);
        if (allByFilterDate.isEmpty()) {
            allByFilterDate = ParsingMovies.parse(filterDate);
            for (MovieEntity movieEntity : allByFilterDate) {
                save(movieEntity);
            }
        }

        resetCacheMap(filterDate, allByFilterDate);
        return allByFilterDate;
    }

    private void resetCacheMap(LocalDate filterDate, List<MovieEntity> allByFilterDate){
        if(cacheMovies.size()==30) cacheMovies.remove(cacheMovies.keySet().iterator().next());
        cacheMovies.put(filterDate, allByFilterDate);
    }
}