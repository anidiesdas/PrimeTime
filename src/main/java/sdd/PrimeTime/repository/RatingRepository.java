package sdd.PrimeTime.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sdd.PrimeTime.dto.MemberAverageRatingDto;
import sdd.PrimeTime.model.Rating;
import sdd.PrimeTime.model.RatingId;

import java.time.LocalDate;
import java.util.List;

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

    @Query("""
    SELECT new sdd.PrimeTime.dto.MemberAverageRatingDto(
        CASE 
            WHEN r.member.id IN (7,8,9,10,11,12) THEN "guest"
            ELSE r.member.name
        END,
        AVG(r.rating)
    )
    FROM Rating r
    GROUP BY 
        CASE 
            WHEN r.member.id IN (7,8,9,10,11,12) THEN "guest"
            ELSE r.member.name
        END
    ORDER BY AVG(r.rating) DESC
    """)
    List<MemberAverageRatingDto> findAverageRatingsGroupedByMember();

    @Query(value = """
    SELECT\s
        m.watch_date AS date,
        ROUND(AVG(r.rating)::numeric, 2) AS avg_rating
    FROM rating r
    JOIN movie m ON r.movie_id = m.id
    WHERE m.watch_date IS NOT NULL AND r.rating IS NOT NULL
    GROUP BY m.watch_date
    ORDER BY m.watch_date
""", nativeQuery = true)
    List<Object[]> getAverageRatingPerDay();

    @Query("SELECT r.movie.title FROM Rating r WHERE r.movie.watchDate BETWEEN :start AND :end GROUP BY r.movie.id, r.movie.title ORDER BY AVG(r.rating) DESC")
    List<String> findBestRatedMovieTitleInMonth(LocalDate start, LocalDate end, Pageable pageable);

    @Query("SELECT r.movie.title FROM Rating r WHERE r.movie.watchDate BETWEEN :start AND :end GROUP BY r.movie.id, r.movie.title ORDER BY AVG(r.rating) ASC")
    List<String> findWorstRatedMovieTitleInMonth(LocalDate start, LocalDate end, Pageable pageable);

}
