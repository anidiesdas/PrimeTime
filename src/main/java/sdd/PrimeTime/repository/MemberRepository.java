package sdd.PrimeTime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sdd.PrimeTime.model.Member;

import java.util.List;

/**
 * Created by Ani Nguyen on 01/05/2025.
 * Author: An Nguyen
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("SELECT m.name, " +
            "COUNT(DISTINCT mov.id), " +
            "COALESCE(SUM(mov.runningTime), 0L) " +
            "FROM Member m " +
            "LEFT JOIN m.ratings r " +
            "LEFT JOIN r.movie mov " +
            "WHERE mov.status = 'COMPLETED' " +
            "GROUP BY m.id, m.name " +
            "ORDER BY COUNT(DISTINCT mov.id) DESC, m.name")
    List<Object[]> findMemberWatchedMoviesStats();
}

