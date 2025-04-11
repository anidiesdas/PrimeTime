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
@RequestMapping("")
public class TmdbController {

    @Autowired
    private TmdbService tmdbService;

    @GetMapping("/search/{query}")
    public String searchMovie(@PathVariable String query) {
        return tmdbService.searchMovie(query);
    }

    @GetMapping("/movies")
    public ResponseEntity<String> getMovies() {
        return ResponseEntity.ok("Hier sind alle Filme!");
    }


}