package sdd.PrimeTime.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Ani Nguyen on 11/04/2025.
 * Author: An Nguyen
 */
@Entity
public class Movie {

    @Id
    @NotNull
    private Long id;

    @NotNull
    private String title;

    @NotNull
    @ElementCollection
    private List<String> genres = new ArrayList<>();

    @NotNull
    private int runningTime;

    @NotNull
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Platform platform;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @NotNull
    private WatchlistStatus status;

    @NotNull
    private LocalDate watchDate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rating> Rating;

    public Movie() {}

    public @NotNull Long getId() {
        return id;
    }

    public void setId(@NotNull Long id) {
        this.id = id;
    }

    public @NotNull String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public @NotNull List<String> getGenres() {
        return genres;
    }

    public void setGenres(@NotNull List<String> genres) {
        this.genres = genres;
    }

    @NotNull
    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(@NotNull int runningTime) {
        this.runningTime = runningTime;
    }

    public @NotNull LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(@NotNull LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public @NotNull WatchlistStatus getStatus() {
        return status;
    }

    public void setStatus(@NotNull WatchlistStatus status) {
        this.status = status;
    }

    public @NotNull LocalDate getWatchDate() {
        return watchDate;
    }

    public void setWatchDate(@NotNull LocalDate watchDate) {
        this.watchDate = watchDate;
    }

    public Set<sdd.PrimeTime.model.Rating> getRating() {
        return Rating;
    }

    public void setRating(Set<sdd.PrimeTime.model.Rating> rating) {
        Rating = rating;
    }
}
