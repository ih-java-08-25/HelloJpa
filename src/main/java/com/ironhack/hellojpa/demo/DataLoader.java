package com.ironhack.hellojpa.demo;

import com.ironhack.hellojpa.model.Movie;
import com.ironhack.hellojpa.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile(value = "demo")
public class DataLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public DataLoader(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println("Initializing database...");

        if (movieRepository.count() > 0) {
            System.out.println("Database already initialized.");
            return;
        }

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
}
