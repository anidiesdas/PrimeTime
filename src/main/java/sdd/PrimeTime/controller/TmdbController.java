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
@RequestMapping("")
public class TmdbController {

    @Autowired
    private TmdbService tmdbService;

    @GetMapping("/search/{query}")
    public String searchMovie(@PathVariable String query) {
        return tmdbService.searchMovie(query);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getMovies() {
        return ResponseEntity.ok("Test. Test. Hier sind alle Filme!");
    }

    @GetMapping("/popular")
    public String getPopularMovies() {
        return tmdbService.getPopularMovies();
    }

    @GetMapping("movie/{id}")
    public String getMovieDetails(@PathVariable int id) {
        return tmdbService.getMovieDetails(id);
    }

}