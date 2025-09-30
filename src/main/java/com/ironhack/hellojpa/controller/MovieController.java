package com.ironhack.hellojpa.controller;

import com.ironhack.hellojpa.dto.UpdateMovieTitleRequest;
import com.ironhack.hellojpa.model.Movie;
import com.ironhack.hellojpa.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Movie> findById(@PathVariable int id) {
        return movieService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie create(@RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @PutMapping("/{id}")
    public Movie update(@PathVariable int id, @RequestBody Movie updatedMovie) {
        return movieService.update(id, updatedMovie);
    }

    @PatchMapping("/{id}/title")
    public Movie updateTitle(@PathVariable int id, @RequestBody UpdateMovieTitleRequest request) {
        return movieService.updateTitle(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable int id) {
        movieService.deleteById(id);
    }


}
