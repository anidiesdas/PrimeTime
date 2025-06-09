package sdd.PrimeTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sdd.PrimeTime.dto.MovieDto;
import sdd.PrimeTime.model.Movie;
import sdd.PrimeTime.model.WatchlistStatus;

import java.util.List;

/**
 * Created by Ani Nguyen on 01/05/2025.
 * Author: An Nguyen
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByStatus(WatchlistStatus status);

}
