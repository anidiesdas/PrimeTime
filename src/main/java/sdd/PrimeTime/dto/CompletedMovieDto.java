package sdd.PrimeTime.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import sdd.PrimeTime.model.Platform;
import sdd.PrimeTime.model.WatchlistStatus;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Ani Nguyen on 06/06/2025.
 * Author: An Nguyen
 */
public class CompletedMovieDto {
    private Long id;
    private String title;
    private int runningTime;
    private List<String> genres;
    private LocalDate watchDate;
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    private List<RatingDto> ratings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public LocalDate getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(LocalDate watchDate) {
        this.watchDate = watchDate;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public List<RatingDto> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingDto> ratings) {
        this.ratings = ratings;
    }
}
