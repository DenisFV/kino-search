package com.example.kino.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "movie")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long movieId;

    @NotNull
    private Long topPosition;

    @NotBlank
    private String originalName;

    @NotNull
    private Integer movieYear;

    @NotNull
    private Double rating;

    @NotNull
    private Long voted;

    @NotNull
    private LocalDate filterDate;

    public MovieEntity() {
    }

    public MovieEntity(Long topPosition, String originalName, Integer movieYear, Double rating, Long voted, LocalDate filterDate) {
        this.topPosition = topPosition;
        this.originalName = originalName;
        this.movieYear = movieYear;
        this.rating = rating;
        this.voted = voted;
        this.filterDate = filterDate;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getTopPosition() {
        return topPosition;
    }

    public void setTopPosition(Long topPosition) {
        this.topPosition = topPosition;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public Integer getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(Integer movieYear) {
        this.movieYear = movieYear;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getVoted() {
        return voted;
    }

    public void setVoted(Long voted) {
        this.voted = voted;
    }

    public LocalDate getFilterDate() {
        return filterDate;
    }

    public void setFilterDate(LocalDate filterDate) {
        this.filterDate = filterDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieEntity that = (MovieEntity) o;
        return Objects.equals(movieId, that.movieId) &&
                Objects.equals(topPosition, that.topPosition) &&
                Objects.equals(originalName, that.originalName) &&
                Objects.equals(movieYear, that.movieYear) &&
                Objects.equals(rating, that.rating) &&
                Objects.equals(voted, that.voted) &&
                Objects.equals(filterDate, that.filterDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, topPosition, originalName, movieYear, rating, voted, filterDate);
    }
}
