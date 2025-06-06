package sdd.PrimeTime.dto;

import java.util.List;

/**
 * Created by Ani Nguyen on 21/05/2025.
 * Author: An Nguyen
 */
public class MovieUpdateDto {
    private Long movieId;
    private List<String> tags;
    private List<RatingDto> ratings;
    private String password;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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

