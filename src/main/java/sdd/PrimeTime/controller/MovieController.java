package sdd.PrimeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdd.PrimeTime.dto.*;
import sdd.PrimeTime.model.*;
import sdd.PrimeTime.service.MovieService;

import java.util.List;
import java.util.Map;

/**
 * Created by Ani Nguyen on 01/05/2025.
 * Author: An Nguyen
 */
@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = {"http://localhost:5173", "https://primetimefrontend.onrender.com"})
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/saving")
    public ResponseEntity<String> saveMovie(@RequestBody MovieWrapperDto wrapper) {
        try {
            String result = movieService.saveMovie(wrapper);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateMovieAndRatings(@RequestBody MovieUpdateDto request) {
        try {
            String result = movieService.updateMovieAndRatings(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long movieId, @RequestParam String password) {
        try {
            String result = movieService.deleteMovie(movieId, password);
            if ("INVALID_PASSWORD".equals(result)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
            }
            if ("MOVIE_NOT_FOUND".equals(result)) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/status/{movieId}")
    public ResponseEntity<MovieStatusResponseDto> getMovieStatus(@PathVariable Long movieId) {
        try {
            MovieStatusResponseDto response = movieService.getMovieStatus(movieId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/status-counts")
    public ResponseEntity<Map<WatchlistStatus, Long>> getMovieStatusCounts() {
        try {
            Map<WatchlistStatus, Long> statusCounts = movieService.getMovieStatusCounts();
            return ResponseEntity.ok(statusCounts);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/top-genres")
    public ResponseEntity<List<String>> getTopGenres() {
        try {
            List<String> topGenres = movieService.getTopGenres();
            return ResponseEntity.ok(topGenres);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/total-runtime")
    public ResponseEntity<Long> getTotalRuntime() {
        try {
            Long totalRuntime = movieService.getTotalRuntime();
            return ResponseEntity.ok(totalRuntime);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{movieId}/tags")
    public ResponseEntity<List<String>> getTagsForMovie(@PathVariable Long movieId) {
        try {
            List<String> tags = movieService.getTagsForMovie(movieId);
            return ResponseEntity.ok(tags);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/plantowatch")
    public ResponseEntity<List<Movie>> getMoviesPlannedToWatch() {
        try {
            List<Movie> movies = movieService.getMoviesPlannedToWatch();
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/dropped")
    public ResponseEntity<List<MovieDto>> getDroppedMovies() {
        try {
            List<MovieDto> result = movieService.getDroppedMovies();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/completed")
    public ResponseEntity<List<CompletedMovieDto>> getCompletedMovies() {
        try {
            List<CompletedMovieDto> result = movieService.getCompletedMovies();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}