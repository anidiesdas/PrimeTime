package sdd.PrimeTime.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sdd.PrimeTime.dto.MovieDto;
import sdd.PrimeTime.dto.MovieRatingDto;
import sdd.PrimeTime.model.Movie;
import sdd.PrimeTime.model.Rating;
import sdd.PrimeTime.model.RatingId;

import java.util.List;
import java.util.Optional;

/**
 * Created by Ani Nguyen on 13/05/2025.
 * Author: An Nguyen
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {

    List<Rating> findByMovieId(Long movieId);

    @Query("SELECT r.movie.title " +
            "FROM Rating r " +
            "GROUP BY r.movie.title " +
            "HAVING COUNT(r.rating) > 0 " +
            "ORDER BY AVG(r.rating) DESC")
    List<String> findTopRatedMovies(Pageable pageable);

    @Query("SELECT r.movie.title " +
            "FROM Rating r " +
            "WHERE r.rating IS NOT NULL " +
            "GROUP BY r.movie.title " +
            "HAVING COUNT(r.rating) > 0 " +
            "ORDER BY AVG(r.rating) ASC")
    List<String> findWorstRatedMovies(Pageable pageable);

}
