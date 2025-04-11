package sdd.PrimeTime.model;

import jakarta.persistence.*;

/**
 * Created by Ani Nguyen on 11/04/2025.
 * Author: An Nguyen
 */
@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private double score;

    public Rating() {}

    public Rating(Movie movie, Member member, double score) {
        this.movie = movie;
        this.member = member;
        this.score = score;

    }

    // Getter und Setter

    public Long getId() {
        return id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}