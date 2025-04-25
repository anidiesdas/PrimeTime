package sdd.PrimeTime.model;

import jakarta.persistence.*;


/**
 * Created by Ani Nguyen on 25/04/2025.
 * Author: An Nguyen
 */
@Entity
public class MemberMovie {

    @EmbeddedId
    private MemberMovieId id;

    @ManyToOne
    @MapsId("MemberId")
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private int rating;

    public MemberMovieId getId() {
        return id;
    }

    public void setId(MemberMovieId id) {
        this.id = id;
    }
}

