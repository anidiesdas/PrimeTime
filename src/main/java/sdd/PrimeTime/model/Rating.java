package sdd.PrimeTime.model;

import jakarta.persistence.*;


/**
 * Created by Ani Nguyen on 25/04/2025.
 * Author: An Nguyen
 */
@Entity
public class Rating {

    @EmbeddedId
    private RatingId id;

    @ManyToOne
    @MapsId("MemberId")
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private int rating;

    public RatingId getId() {
        return id;
    }

    public void setId(RatingId id) {
        this.id = id;
    }
}

