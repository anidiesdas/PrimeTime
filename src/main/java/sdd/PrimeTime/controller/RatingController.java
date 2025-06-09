package sdd.PrimeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdd.PrimeTime.dto.*;
import sdd.PrimeTime.service.RatingService;

import java.util.List;

/**
 * Created by Ani Nguyen on 20/05/2025.
 * Author: An Nguyen
 */
@RestController
@RequestMapping("/ratings")
@CrossOrigin(origins = {"http://localhost:5173", "https://primetimefrontend.onrender.com"})
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<RatingDto>> getRatingsForMovie(@PathVariable Long movieId) {
        try {
            List<RatingDto> ratingDtos = ratingService.getRatingsForMovie(movieId);
            return ResponseEntity.ok(ratingDtos);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/average-rating")
    public ResponseEntity<Double> getAverageRating() {
        try {
            Double average = ratingService.getAverageRating();
            return ResponseEntity.ok(average);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<String>> getTopRatedMovies() {
        try {
            List<String> topRatedMovies = ratingService.getTopRatedMovies();
            return ResponseEntity.ok(topRatedMovies);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/worst-rated")
    public ResponseEntity<List<String>> getWorstRatedMovies() {
        try {
            List<String> worstRatedMovies = ratingService.getWorstRatedMovies();
            return ResponseEntity.ok(worstRatedMovies);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/average-by-member")
    public ResponseEntity<List<MemberAverageRatingDto>> getAverageRatingsByMember() {
        try {
            List<MemberAverageRatingDto> averageRatings = ratingService.getAverageRatingsByMember();
            return ResponseEntity.ok(averageRatings);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/global-progress")
    public ResponseEntity<List<GlobalRatingProgressDto>> getGlobalRatingProgress() {
        try {
            List<GlobalRatingProgressDto> result = ratingService.getGlobalRatingProgress();
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}