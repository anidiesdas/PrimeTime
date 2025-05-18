package sdd.PrimeTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
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

    //TODO hier DB-Anfragen für Analysen

}
