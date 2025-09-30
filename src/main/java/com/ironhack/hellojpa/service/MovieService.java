package com.ironhack.hellojpa.service;

import com.ironhack.hellojpa.dto.UpdateMovieTitleRequest;
import com.ironhack.hellojpa.model.Movie;
import com.ironhack.hellojpa.repository.MovieRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;


    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAll() {
        System.out.println("Retrieving all movies from the database...");
        return movieRepository.findAll();
    }

    public Optional<Movie> findById(int id) {
        System.out.println("Retrieving Movie with ID: " + id);
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()){
            Movie movie = optionalMovie.get();
            System.out.println("Found Movie: " + movie.getTitle());
        } else {
            System.out.println("Movie with ID " + id + " not found.");
        }
        return optionalMovie;
    }


    public Movie save(Movie movie) {
        System.out.println("Saving a new movie to the database: " + movie.getTitle());
        return movieRepository.save(movie);
    }

    public Movie update(int id, Movie updatedMovie) {
        Movie movieToUpdate = movieRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Movie not found with ID: " + id));

        movieToUpdate.setTitle(updatedMovie.getTitle());
        movieToUpdate.setDirector(updatedMovie.getDirector());
        movieToUpdate.setGenre(updatedMovie.getGenre());
        movieToUpdate.setReleaseDate(updatedMovie.getReleaseDate());

        return movieRepository.save(movieToUpdate);
    }

    public Movie updateTitle(int id, UpdateMovieTitleRequest request) {
        if (id != request.getId()) {
            throw new IllegalArgumentException("ID in path and request body do not match");
        }

        Movie movieToUpdate = movieRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Movie not found with ID: " + id));

        movieToUpdate.setTitle(request.getTitle());
        return movieRepository.save(movieToUpdate);
    }

    public void deleteById(int id) {
        movieRepository.deleteById(id);
    }


}
