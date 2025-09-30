package com.ironhack.hellojpa.service;

import com.ironhack.hellojpa.model.Movie;
import com.ironhack.hellojpa.repository.MovieRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

@PostConstruct
    private void init() {
        System.out.println("Initializing database...");

        Movie movie1 = new Movie("Harry Potter and the Sorcerer's Stone",
                "Chris Columbus",
                "Fantasy",
                LocalDate.of(2001, 11, 16));

        Movie movie2 = new Movie("The Meg",
                "Jon Turteltaub",
                "Action",
                java.time.LocalDate.of(2018, 8, 10));

        Movie movie3 = new Movie("Waking Life",
                "Richard Linklater",
                "Animation",
                java.time.LocalDate.of(2001, 7, 20));

        movieRepository.saveAll(List.of(movie1, movie2, movie3));
    }

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        System.out.println("Retrieving all movies from the database...");
        return movieRepository.findAll();
    }


    public Movie save(Movie movie) {
        System.out.println("Saving a new movie to the database: " + movie.getTitle());
        return movieRepository.save(movie);
    }
}
