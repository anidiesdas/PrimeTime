package sdd.PrimeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdd.PrimeTime.dto.MovieDto;
import sdd.PrimeTime.dto.MovieRatingDto;
import sdd.PrimeTime.dto.RatingDto;
import sdd.PrimeTime.model.Rating;
import sdd.PrimeTime.repository.MemberRepository;
import sdd.PrimeTime.repository.MovieRepository;
import sdd.PrimeTime.repository.RatingRepository;

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
    private RatingRepository ratingRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<?> getRatingsForMovie(@PathVariable Long movieId) {
        List<Rating> ratings = ratingRepository.findByMovieId(movieId);
        List<RatingDto> ratingDtos = ratings.stream().map(r -> {
            RatingDto dto = new RatingDto();
            dto.setMovieId(r.getMovie().getId());
            dto.setMemberId(r.getMember().getId());
            dto.setRating(r.getRating());
            dto.setMemberName(r.getMember().getName());
            return dto;
        }).toList();
        return ResponseEntity.ok(ratingDtos);
    }

    @GetMapping("/average-rating")
    public ResponseEntity<Double> getAverageRating() {
        List<Rating> ratings = ratingRepository.findAll();
        double average = ratings.stream()
                .filter(r -> r.getRating() != null)
                .mapToDouble(Rating::getRating)
                .average()
                .orElse(0.0);

        double rounded = Math.round(average * 1000.0) / 1000.0;

        return ResponseEntity.ok(rounded);
    }

    @GetMapping("/top-rated")
    public List<String> getTopRatedMovies() {
        return ratingRepository.findTopRatedMovies(PageRequest.of(0, 3));
    }

    @GetMapping("/worst-rated")
    public List<String> getWorstRatedMovies() {
        return ratingRepository.findWorstRatedMovies(PageRequest.of(0, 3));
    }

}
