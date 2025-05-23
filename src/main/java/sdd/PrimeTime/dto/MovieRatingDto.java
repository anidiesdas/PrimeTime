package sdd.PrimeTime.dto;

/**
 * Created by Ani Nguyen on 21/05/2025.
 * Author: An Nguyen
 */
public class MovieRatingDto {
    private String title;
    private Double averageRating;

    public MovieRatingDto(String title, Double averageRating) {
        this.title = title;
        this.averageRating = averageRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) { this.title = title; }

    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) { this.averageRating = averageRating; }
}