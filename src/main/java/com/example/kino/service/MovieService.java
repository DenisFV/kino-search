package com.example.kino.service;

import com.example.kino.entity.MovieEntity;

import java.util.List;

public interface MovieService {
    void save(MovieEntity movieEntity);
    List<MovieEntity> findAllByFilterDate(String filterDate);
}