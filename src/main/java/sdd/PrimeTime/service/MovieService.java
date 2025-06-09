package sdd.PrimeTime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sdd.PrimeTime.dto.*;
import sdd.PrimeTime.model.*;
import sdd.PrimeTime.repository.MemberRepository;
import sdd.PrimeTime.repository.MovieRepository;
import sdd.PrimeTime.repository.RatingRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordService passwordService;

    public String saveMovie(MovieWrapperDto wrapper) {
        MovieDto dto = wrapper.getMovie();

        if (dto.getStatus() == WatchlistStatus.DROPPED || dto.getStatus() == WatchlistStatus.COMPLETED) {
            if (!passwordService.validatePassword(wrapper.getPassword())) {
                return "INVALID_PASSWORD";
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

        return "Movie and ratings saved.";
    }

    public String updateMovieAndRatings(MovieUpdateDto request) {
        if (!passwordService.validatePassword(request.getPassword())) {
            return "INVALID_PASSWORD";
        }

        Optional<Movie> movieOpt = movieRepository.findById(request.getMovieId());
        if (movieOpt.isEmpty()) {
            return null;
        }

        Movie movie = movieOpt.get();

        if (request.getTags() != null) {
            movie.setTags(request.getTags());
            movieRepository.save(movie);
        }

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

        return "Movie updated with new tags and/or ratings.";
    }

    public String deleteMovie(Long movieId, String password) {
        if (!passwordService.validatePassword(password)) {
            return "INVALID_PASSWORD";
        }

        Optional<Movie> movieOpt = movieRepository.findById(movieId);
        if (movieOpt.isEmpty()) {
            return "MOVIE_NOT_FOUND";
        }

        movieRepository.delete(movieOpt.get());
        return "Movie deleted successfully.";
    }

    public MovieStatusResponseDto getMovieStatus(Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            return null;
        }

        Movie foundMovie = movie.get();
        return new MovieStatusResponseDto(
                foundMovie.getStatus() != null ? foundMovie.getStatus().toString() : null,
                foundMovie.getWatchDate(),
                foundMovie.getPlatform()
        );
    }

    public Map<WatchlistStatus, Long> getMovieStatusCounts() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .collect(Collectors.groupingBy(Movie::getStatus, Collectors.counting()));
    }

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

    public Long getTotalRuntime() {
        return movieRepository.findAll().stream()
                .filter(m -> m.getStatus() == WatchlistStatus.COMPLETED)
                .mapToLong(Movie::getRunningTime)
                .sum();
    }

    public List<String> getTagsForMovie(Long movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movie.map(Movie::getTags).orElse(null);

    }

    public List<Movie> getMoviesPlannedToWatch() {
        return movieRepository.findByStatus(WatchlistStatus.PLAN_TO_WATCH);
    }

    public List<MovieDto> getDroppedMovies() {
        List<Movie> movies = movieRepository.findByStatus(WatchlistStatus.DROPPED);
        return movies.stream()
                .map(MovieDto::new)
                .toList();
    }

    public List<CompletedMovieDto> getCompletedMovies() {
        List<Movie> completedMovies = movieRepository.findByStatus(WatchlistStatus.COMPLETED);

        return completedMovies.stream().map(movie -> {
            CompletedMovieDto dto = new CompletedMovieDto();
            dto.setId(movie.getId());
            dto.setTitle(movie.getTitle());
            dto.setRunningTime(movie.getRunningTime());
            dto.setGenres(movie.getGenres());
            dto.setWatchDate(movie.getWatchDate());
            dto.setReleaseDate(movie.getReleaseDate());
            dto.setPlatform(movie.getPlatform());
            dto.setTags(movie.getTags());

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
    }
}