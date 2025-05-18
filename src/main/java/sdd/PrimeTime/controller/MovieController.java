package sdd.PrimeTime.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdd.PrimeTime.dto.MovieDto;
import sdd.PrimeTime.dto.MovieWrapperDto;
import sdd.PrimeTime.dto.MovieWrapperDto;
import sdd.PrimeTime.dto.RatingDto;
import sdd.PrimeTime.model.*;
import sdd.PrimeTime.repository.MemberRepository;
import sdd.PrimeTime.repository.MovieRepository;
import sdd.PrimeTime.repository.RatingRepository;
import sdd.PrimeTime.service.MovieService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ani Nguyen on 01/05/2025.
 * Author: An Nguyen
 */
@RestController
@RequestMapping("/saving")
@CrossOrigin(origins = "http://localhost:5173")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MemberRepository memberRepository;
    //TODO @PostMapping zum Speichern von Movies

    @PostMapping("")
    public ResponseEntity<?> saveMovie(@RequestBody MovieWrapperDto wrapper) {
        MovieDto dto = wrapper.getMovie();
        List<RatingDto> ratingDtos = wrapper.getRatings();

        // Filmobjekt erstellen oder aktualisieren
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

        // Film zuerst speichern, damit er in den Ratings referenziert werden kann
        movie = movieRepository.save(movie);

        // Ratings speichern (vorher sicherstellen, dass Teilnehmer existieren)
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

//    @PostMapping
//    public ResponseEntity<?> saveMovie(@RequestBody MovieWrapperDto wrapper) {
//        movieService.saveMovie(wrapper.getMovie());
//        return ResponseEntity.ok().build();
//    }

//    TEST
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @PostMapping("")
//    public ResponseEntity<?> saveMovie(@RequestBody MovieWrapperDto wrapper) throws JsonProcessingException {
//        System.out.println("Gesamter empfangener Payload:");
//        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(wrapper));
//        return ResponseEntity.ok().build();
//    }


}