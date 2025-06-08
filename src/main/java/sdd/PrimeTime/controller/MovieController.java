package sdd.PrimeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

        if (dto.getStatus() == WatchlistStatus.DROPPED || dto.getStatus() == WatchlistStatus.COMPLETED) {
            if (!savePassword.equals(wrapper.getPassword())) {
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
        if (!savePassword.equals(request.getPassword())) {
            return ResponseEntity.ok("INVALID_PASSWORD");
        }

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

    @DeleteMapping("/{movieId}")
    public ResponseEntity<?> deleteMovie(
            @PathVariable Long movieId,
            @RequestParam String password
    ) {
        if (!savePassword.equals(password)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("INVALID_PASSWORD");
        }

        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if (movieOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        movieRepository.delete(movieOpt.get());
        return ResponseEntity.ok("Movie deleted successfully.");
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
                .limit(5)
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

    @GetMapping("/plantowatch")
    public ResponseEntity<List<Movie>> getMoviesPlannedToWatch() {
        List<Movie> movies = movieRepository.findByStatus(WatchlistStatus.PLAN_TO_WATCH);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/dropped")
    public ResponseEntity<List<MovieDto>> getDroppedMovies() {
        List<Movie> movies = movieRepository.findByStatus(WatchlistStatus.DROPPED);
        List<MovieDto> result = movies.stream()
                .map(MovieDto::new)
                .toList();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<CompletedMovieDto>> getCompletedMovies() {
        List<Movie> completedMovies = movieRepository.findByStatus(WatchlistStatus.COMPLETED);

        List<CompletedMovieDto> result = completedMovies.stream().map(movie -> {
            CompletedMovieDto dto = new CompletedMovieDto();
            dto.setId(movie.getId());
            dto.setTitle(movie.getTitle());
            dto.setRunningTime(movie.getRunningTime());
            dto.setGenres(movie.getGenres());
            dto.setWatchDate(movie.getWatchDate());
            dto.setReleaseDate(movie.getReleaseDate());
            dto.setPlatform(movie.getPlatform());
            dto.setTags(movie.getTags());

            // Bewertungen umwandeln
            List<RatingDto> ratingDtos = movie.getRating().stream()
                    .map(rating -> {
                        RatingDto ratingDto = new RatingDto();
                        ratingDto.setMemberId(rating.getMember().getId());
                        ratingDto.setRating(rating.getRating());
                        return ratingDto;
                    }).toList();

            dto.setRatings(ratingDtos);
            return dto;
        }).toList();

        return ResponseEntity.ok(result);
    }

}