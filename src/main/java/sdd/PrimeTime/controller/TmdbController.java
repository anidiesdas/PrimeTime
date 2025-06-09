package sdd.PrimeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdd.PrimeTime.service.TmdbService;

/**
 * Created by Ani Nguyen on 11/04/2025.
 * Author: An Nguyen
 */
@RestController
@CrossOrigin(origins = {"http://localhost:5173", "https://primetimefrontend.onrender.com"})
@RequestMapping("/")
public class TmdbController {

    @Autowired
    private TmdbService tmdbService;

    @GetMapping("/search/{query}")
    public ResponseEntity<String> searchMovie(@PathVariable String query) {
        try {
            String searchResult = tmdbService.searchMovie(query);
            return ResponseEntity.ok(searchResult);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> getMovies() {
        return ResponseEntity.ok("Test. Test. Hier sind alle Filme!");
    }

    @GetMapping("/popular")
    public ResponseEntity<String> getPopularMovies() {
        try {
            String popularMovies = tmdbService.getPopularMovies();
            return ResponseEntity.ok(popularMovies);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<String> getMovieDetails(@PathVariable int id) {
        try {
            String movieDetails = tmdbService.getMovieDetails(id);
            return ResponseEntity.ok(movieDetails);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}