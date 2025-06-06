package sdd.PrimeTime.dto;

import java.util.List;

/**
 * Created by Ani Nguyen on 17/05/2025.
 * Author: An Nguyen
 */
public class MovieWrapperDto {
    private MovieDto movie;
    private List<RatingDto> ratings;
    private String password;

    public MovieWrapperDto() {}

    public MovieDto getMovie() {
        return movie;
    }

    public void setMovie(MovieDto movie) {
        this.movie = movie;
    }

    public List<RatingDto> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingDto> ratings) {
        this.ratings = ratings;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) { this.password = password; }
}

