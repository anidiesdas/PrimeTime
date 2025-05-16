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

    public Rating() {}

    public RatingId getId() {
        return id;
    }

    public void setId(RatingId id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

