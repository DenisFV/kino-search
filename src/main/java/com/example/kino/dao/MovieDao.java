package com.example.kino.dao;

import com.example.kino.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovieDao extends CrudRepository<MovieEntity, Long> {
    List<MovieEntity> findAllByFilterDate(LocalDate filterDate);
}