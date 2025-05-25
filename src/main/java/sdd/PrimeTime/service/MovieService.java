package sdd.PrimeTime.service;

import org.springframework.stereotype.Service;
import sdd.PrimeTime.dto.MovieDto;
import sdd.PrimeTime.model.Movie;
import sdd.PrimeTime.repository.MovieRepository;

/**
 * Created by Ani Nguyen on 13/05/2025.
 * Author: An Nguyen
 */
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void saveMovie(MovieDto dto) {
        Movie movie = new Movie();
        movie.setId(dto.getId());
        movie.setTitle(dto.getTitle());
        movie.setRunningTime(dto.getRunningTime());
        movie.setReleaseDate(dto.getReleaseDate());
        movie.setGenres(dto.getGenres());
        movie.setStatus(dto.getStatus());
        movie.setWatchDate(dto.getWatchDate());
        movie.setTags(dto.getTags());
        movieRepository.save(movie);
    }
}
