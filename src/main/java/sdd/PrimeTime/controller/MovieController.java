package sdd.PrimeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdd.PrimeTime.dto.MovieDto;
import sdd.PrimeTime.dto.MovieWrapperDto;
import sdd.PrimeTime.dto.RatingDto;
import sdd.PrimeTime.model.*;
import sdd.PrimeTime.repository.MemberRepository;
import sdd.PrimeTime.repository.MovieRepository;
import sdd.PrimeTime.repository.RatingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ani Nguyen on 01/05/2025.
 * Author: An Nguyen
 */
@RestController
@RequestMapping("/movie")
@CrossOrigin(origins = "http://localhost:5173")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MemberRepository memberRepository;

    //TODO @PostMapping zum Speichern von Movies

    //    TEST
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @PostMapping("")
//    public ResponseEntity<?> saveMovie(@RequestBody MovieWrapperDto wrapper) throws JsonProcessingException {
//        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(wrapper));
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/saving")
    public ResponseEntity<?> saveMovie(@RequestBody MovieWrapperDto wrapper) {
        MovieDto dto = wrapper.getMovie();
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
            return ResponseEntity.status(404).body("Movie not found");  // <-- jetzt wirklich 404
        }
        return ResponseEntity.ok(movie.get().getStatus());
    }

    @GetMapping("/ratings/{movieId}")
    public ResponseEntity<?> getRatingsForMovie(@PathVariable Long movieId) {
        List<Rating> ratings = ratingRepository.findByMovieId(movieId);
        List<RatingDto> ratingDtos = ratings.stream().map(r -> {
            RatingDto dto = new RatingDto();
            dto.setMovieId(r.getMovie().getId());
            dto.setMemberId(r.getMember().getId());
            dto.setRating(r.getRating());
            dto.setMemberName(r.getMember().getName()); // <- memberName ergänzen
            return dto;
        }).toList();
        return ResponseEntity.ok(ratingDtos);
    }

    @PostMapping("/update")
    public ResponseEntity<?> addRatings(@RequestBody List<RatingDto> ratingDtos) {
        for (RatingDto ratingDto : ratingDtos) {
            Optional<Movie> movieOpt = movieRepository.findById(ratingDto.getMovieId());
            Optional<Member> memberOpt = memberRepository.findById(ratingDto.getMemberId());

            if (movieOpt.isEmpty() || memberOpt.isEmpty()) {
                continue;
            }

            RatingId ratingId = new RatingId(ratingDto.getMemberId(), ratingDto.getMovieId());
            Rating rating = ratingRepository.findById(ratingId).orElse(new Rating());

            rating.setId(ratingId);
            rating.setMovie(movieOpt.get());
            rating.setMember(memberOpt.get());
            rating.setRating(ratingDto.getRating());

            ratingRepository.save(rating);
        }

        return ResponseEntity.ok("Ratings gespeichert.");
    }
}