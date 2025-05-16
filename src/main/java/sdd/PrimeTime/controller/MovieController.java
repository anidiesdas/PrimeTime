package sdd.PrimeTime.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sdd.PrimeTime.dto.MovieSaveDto;
import sdd.PrimeTime.model.*;
import sdd.PrimeTime.repository.MemberRepository;
import sdd.PrimeTime.repository.MovieRepository;
import sdd.PrimeTime.repository.RatingRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Ani Nguyen on 01/05/2025.
 * Author: An Nguyen
 */
@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:5173")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MemberRepository memberRepository;
    //TODO @PostMapping zum Speichern von Movies

    @PostMapping("/save")
    public ResponseEntity<?> saveMovie(@RequestBody MovieSaveDto dto) {
        Movie movie = new Movie();
        movie.setId(dto.id);
        movie.setTitle(dto.title);
        movie.setGenres(List.of(dto.genre));
        movie.setRunningTime(dto.runningTime);
        movie.setReleaseDate(LocalDate.parse(dto.releaseDate));
        movie.setWatchDate(LocalDate.parse(dto.watchDate));
        movie.setPlatform(Platform.valueOf(dto.platform));
        movie.setStatus(WatchlistStatus.valueOf(dto.status));

        movieRepository.save(movie);

        if (dto.users != null) {
            for (MovieSaveDto.MemberRatingDto userDto : dto.users) {
                Member member = memberRepository.findById(userDto.id)
                        .orElseThrow(() -> new RuntimeException("Member not found"));

                RatingId ratingId = new RatingId(movie.getId(), member.getId());

                Rating rating = new Rating();
                rating.setId(ratingId);
                rating.setMovie(movie);
                rating.setMember(member);
                rating.setRating(Integer.parseInt(userDto.rating));
                ratingRepository.save(rating);
            }
        }

        return ResponseEntity.ok("Movie and ratings saved");
    }

}