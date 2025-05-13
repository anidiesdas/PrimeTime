package sdd.PrimeTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdd.PrimeTime.model.Rating;

/**
 * Created by Ani Nguyen on 13/05/2025.
 * Author: An Nguyen
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    //TODO hier DB-Anfragen für Analysen

}
