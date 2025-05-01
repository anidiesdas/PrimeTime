package sdd.PrimeTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdd.PrimeTime.model.Movie;

/**
 * Created by Ani Nguyen on 01/05/2025.
 * Author: An Nguyen
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
