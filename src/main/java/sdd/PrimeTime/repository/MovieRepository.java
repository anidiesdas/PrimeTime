package sdd.PrimeTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sdd.PrimeTime.model.Movie;
import sdd.PrimeTime.model.WatchlistStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Ani Nguyen on 01/05/2025.
 * Author: An Nguyen
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByStatus(WatchlistStatus status);

    @Query("SELECT COUNT(m) FROM Movie m WHERE m.watchDate BETWEEN :start AND :end")
    int countMoviesWatchedInMonth(LocalDate start, LocalDate end);

    @Query("SELECT SUM(m.runningTime) FROM Movie m WHERE m.watchDate BETWEEN :start AND :end")
    Integer sumRuntimeInMonth(LocalDate start, LocalDate end);

    @Query("SELECT m FROM Movie m WHERE m.watchDate BETWEEN :start AND :end")
    List<Movie> findAllWatchedInMonth(LocalDate start, LocalDate end);

}