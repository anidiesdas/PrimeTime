package sdd.PrimeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdd.PrimeTime.dto.*;
import sdd.PrimeTime.model.*;
import sdd.PrimeTime.repository.MemberRepository;
import sdd.PrimeTime.repository.MovieRepository;
import sdd.PrimeTime.repository.RatingRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Ani Nguyen on 01/05/2025.
 * Author: An Nguyen
 */
@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = {"http://localhost:5173", "https://primetimefrontend.onrender.com"})
public class MovieController {

    @Value("${app.movie.save.password}")
    private String savePassword;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/saving")
    public ResponseEntity<?> saveMovie(@RequestBody MovieWrapperDto wrapper) {
        MovieDto dto = wrapper.getMovie();

        if (dto.getStatus() == WatchlistStatus.DROPPED || dto.getStatus() == WatchlistStatus.COMPLETED) {            if (!savePassword.equals(wrapper.getPassword())) {
                return ResponseEntity.ok("INVALID_PASSWORD");
            }
        }

        List<RatingDto> ratingDtos = wrapper.getRatings();
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setTitle(dto.getTitle());
        movie.setGenres(dto.getGenres());
        movie.setRunningTime(dto.getRunningTime());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setPlatform(dto.getPlatform());
        movie.setTags(dto.getTags());
        movie.setStatus(dto.getStatus());
        movie.setWatchDate(dto.getWatchDate());

        movie = movieRepository.save(movie);

        for (RatingDto ratingDto : ratingDtos) {
            Optional<Member> optionalMember = memberRepository.findById(ratingDto.getMemberId());
            if (optionalMember.isEmpty()) continue;

            Member member = optionalMember.get();

            RatingId id = new RatingId(member.getId(), movie.getId());
            Rating rating = new Rating();
            rating.setId(id);
            rating.setMember(member);
            rating.setMovie(movie);
            rating.setRating(ratingDto.getRating());

            ratingRepository.save(rating);
        }

        return ResponseEntity.ok("Movie and ratings saved.");
    }

    @GetMapping("/status/{movieId}")
    public ResponseEntity<?> getMovieStatus(@PathVariable Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            return ResponseEntity.ok(null);
        }

        Movie foundMovie = movie.get();
        MovieStatusResponseDto response = new MovieStatusResponseDto(
                foundMovie.getStatus() != null ? foundMovie.getStatus().toString() : null,
                foundMovie.getWatchDate()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateMovieAndRatings(@RequestBody MovieUpdateDto request) {
        Optional<Movie> movieOpt = movieRepository.findById(request.getMovieId());

        if (movieOpt.isEmpty()) {
            return ResponseEntity.ok(null);
        }

        Movie movie = movieOpt.get();

        // tags aktualisieren
        if (request.getTags() != null) {
            movie.setTags(request.getTags());
            movieRepository.save(movie);
        }

        // ratings aktualisieren
        if (request.getRatings() != null) {
            for (RatingDto ratingDto : request.getRatings()) {
                Optional<Member> memberOpt = memberRepository.findById(ratingDto.getMemberId());
                if (memberOpt.isEmpty()) continue;

                RatingId ratingId = new RatingId(ratingDto.getMemberId(), movie.getId());
                Rating rating = ratingRepository.findById(ratingId).orElse(new Rating());

                rating.setId(ratingId);
                rating.setMovie(movie);
                rating.setMember(memberOpt.get());
                rating.setRating(ratingDto.getRating());

                ratingRepository.save(rating);
            }
        }

        return ResponseEntity.ok("Movie updated with new tags and/or ratings.");
    }


    @GetMapping("/status-counts")
    public Map<WatchlistStatus, Long> getMovieStatusCounts() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .collect(Collectors.groupingBy(Movie::getStatus, Collectors.counting()));
    }

    @GetMapping("/top-genres")
    public List<String> getTopGenres() {
        List<Movie> movies = movieRepository.findAll().stream()
                .filter(m -> m.getStatus() == WatchlistStatus.COMPLETED || m.getStatus() == WatchlistStatus.DROPPED)
                .toList();

        Map<String, Long> genreCount = movies.stream()
                .flatMap(m -> m.getGenres().stream())
                .collect(Collectors.groupingBy(g -> g, Collectors.counting()));

        return genreCount.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }

    @GetMapping("/total-runtime")
    public Long getTotalRuntime() {
        return movieRepository.findAll().stream()
                .filter(m -> m.getStatus() == WatchlistStatus.COMPLETED)
                .mapToLong(Movie::getRunningTime)
                .sum();
    }

    @GetMapping("/{movieId}/tags")
    public ResponseEntity<?> getTagsForMovie(@PathVariable Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            return ResponseEntity.ok(null);
        }

        List<String> tags = movie.get().getTags();
        return ResponseEntity.ok(tags);
    }

}