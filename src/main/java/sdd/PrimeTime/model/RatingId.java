package sdd.PrimeTime.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Ani Nguyen on 25/04/2025.
 * Author: An Nguyen
 */
@Embeddable
public class RatingId implements Serializable {

    @Column(name = "member_id")
    private Long MemberId;

    @Column(name = "movie_id")
    private Long movieId;

    public RatingId() {}

    public RatingId(Long participantId, Long movieId) {
        this.MemberId = participantId;
        this.movieId = movieId;
    }

    public Long getMemberId() {
        return MemberId;
    }

    public void setMemberId(Long memberId) {
        this.MemberId = memberId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RatingId that = (RatingId) obj;
        return Objects.equals(MemberId, that.MemberId) && Objects.equals(movieId, that.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(MemberId, movieId);
    }
}

